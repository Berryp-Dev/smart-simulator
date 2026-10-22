package com.capgemini.smart.simulator.account.raccordopartitario.support;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.RecordComponent;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Utility di test: costruisce per riflessione istanze completamente valorizzate di DTO (bean con setter)
 * e di modelli di dominio (record), e verifica che ogni campo sia valorizzato (o nullo).
 */
public final class SamplePopulator {

    private static final OffsetDateTime SAMPLE_DATE_TIME = OffsetDateTime.parse("2024-01-18T15:04:05Z");

    private SamplePopulator() {
    }

    public static <T> T populate(Class<T> type) {
        return type.cast(build(type, type));
    }

    /** Come {@link #populate}, ma tutti i componenti del record sono null. */
    public static <T> T populateWithNulls(Class<T> type) {
        try {
            RecordComponent[] components = type.getRecordComponents();
            Class<?>[] types = new Class<?>[components.length];
            for (int i = 0; i < components.length; i++) {
                types[i] = components[i].getType();
            }
            return type.getDeclaredConstructor(types).newInstance(new Object[components.length]);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static Object build(Class<?> raw, Type generic) {
        if (raw == String.class) {
            return "sample";
        }
        if (raw == Boolean.class) {
            return Boolean.TRUE;
        }
        if (raw == Double.class) {
            return 1.5d;
        }
        if (raw == OffsetDateTime.class) {
            return SAMPLE_DATE_TIME;
        }
        if (raw == Map.class) {
            return Map.of("key", "value");
        }
        if (raw == List.class) {
            Type element = ((ParameterizedType) generic).getActualTypeArguments()[0];
            return List.of(build(rawOf(element), element));
        }
        if (raw.isEnum()) {
            return raw.getEnumConstants()[0];
        }
        if (raw.isRecord()) {
            return buildRecord(raw);
        }
        return buildBean(raw);
    }

    private static Class<?> rawOf(Type type) {
        return type instanceof ParameterizedType parameterized ? (Class<?>) parameterized.getRawType() : (Class<?>) type;
    }

    private static Object buildRecord(Class<?> type) {
        try {
            RecordComponent[] components = type.getRecordComponents();
            Class<?>[] types = new Class<?>[components.length];
            Object[] values = new Object[components.length];
            for (int i = 0; i < components.length; i++) {
                types[i] = components[i].getType();
                values[i] = build(types[i], components[i].getGenericType());
            }
            return type.getDeclaredConstructor(types).newInstance(values);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static Object buildBean(Class<?> type) {
        try {
            Object bean = type.getDeclaredConstructor().newInstance();
            for (Method method : type.getMethods()) {
                if (method.getName().startsWith("set") && method.getParameterCount() == 1) {
                    method.invoke(bean, build(method.getParameterTypes()[0], method.getGenericParameterTypes()[0]));
                }
            }
            return bean;
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /** Verifica ricorsivamente che nessun componente/proprieta' sia null e che liste e mappe non siano vuote. */
    public static void assertFullyPopulated(Object value) {
        check(value, "root");
    }

    private static void check(Object value, String path) {
        assertNotNull(value, "Campo non valorizzato: " + path);
        if (value instanceof Collection<?> collection) {
            assertFalse(collection.isEmpty(), "Lista vuota: " + path);
            int index = 0;
            for (Object element : collection) {
                check(element, path + "[" + index++ + "]");
            }
        } else if (value instanceof Map<?, ?> map) {
            assertFalse(map.isEmpty(), "Mappa vuota: " + path);
        } else if (value.getClass().isRecord()) {
            for (RecordComponent component : value.getClass().getRecordComponents()) {
                check(invoke(component.getAccessor(), value), path + "." + component.getName());
            }
        } else if (isProjectBean(value)) {
            for (Method getter : getters(value.getClass())) {
                check(invoke(getter, value), path + "." + getter.getName());
            }
        }
    }

    public static void assertAllComponentsNull(Object record) {
        assertNotNull(record);
        for (RecordComponent component : record.getClass().getRecordComponents()) {
            assertNull(invoke(component.getAccessor(), record), "Atteso null: " + component.getName());
        }
    }

    public static void assertAllPropertiesNull(Object bean) {
        assertNotNull(bean);
        for (Method getter : getters(bean.getClass())) {
            assertNull(invoke(getter, bean), "Atteso null: " + getter.getName());
        }
    }

    private static boolean isProjectBean(Object value) {
        return !(value instanceof Enum<?>) && value.getClass().getPackageName().startsWith("com.capgemini");
    }

    private static List<Method> getters(Class<?> type) {
        return Arrays.stream(type.getMethods())
                .filter(method -> method.getName().startsWith("get") && method.getParameterCount() == 0
                        && method.getDeclaringClass() != Object.class)
                .toList();
    }

    private static Object invoke(Method method, Object target) {
        try {
            return method.invoke(target);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
