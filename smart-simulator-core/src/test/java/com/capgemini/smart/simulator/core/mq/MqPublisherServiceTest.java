package com.capgemini.smart.simulator.core.mq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.capgemini.smart.simulator.core.logging.LogCapture;
import jakarta.jms.JMSException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.UncategorizedJmsException;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MqPublisherServiceTest {

    private static final String QUEUE = "DEV.QUEUE.2";
    private static final String XML = "<InvioAggiornamentoStato><IdPratica>123</IdPratica></InvioAggiornamentoStato>";

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private MqPublisherService mqPublisherService;

    @Test
    void sendRawText_ok_logsInfoBeforeWithFullPayloadAndInfoAfterWithoutPayload() {
        try (LogCapture capture = new LogCapture(MqPublisherService.class)) {
            mqPublisherService.sendRawText(QUEUE, XML);

            verify(jmsTemplate).send(eq(QUEUE), any(MessageCreator.class));
            List<ILoggingEvent> events = capture.getEvents();
            assertEquals(2, events.size());
            ILoggingEvent before = events.get(0);
            assertEquals(Level.INFO, before.getLevel());
            assertTrue(before.getFormattedMessage().startsWith("Scrittura MQ"));
            assertTrue(before.getFormattedMessage().contains(QUEUE));
            assertTrue(before.getFormattedMessage().contains(XML));
            ILoggingEvent after = events.get(1);
            assertEquals(Level.INFO, after.getLevel());
            assertTrue(after.getFormattedMessage().startsWith("Scrittura MQ"));
            assertTrue(after.getFormattedMessage().contains("esito positivo"));
            assertTrue(after.getFormattedMessage().contains(QUEUE));
            assertTrue(!after.getFormattedMessage().contains(XML));
        }
    }

    @Test
    void sendRawText_error_logsErrorWithQueuePayloadAndStackTraceThenRethrowsSameException() {
        JmsException failure = new UncategorizedJmsException("coda non raggiungibile", new JMSException("boom"));
        doThrow(failure).when(jmsTemplate).send(eq(QUEUE), any(MessageCreator.class));

        try (LogCapture capture = new LogCapture(MqPublisherService.class)) {
            JmsException thrown = assertThrows(JmsException.class, () -> mqPublisherService.sendRawText(QUEUE, XML));

            assertSame(failure, thrown);
            List<ILoggingEvent> events = capture.getEvents();
            assertEquals(2, events.size());
            ILoggingEvent error = events.get(1);
            assertEquals(Level.ERROR, error.getLevel());
            assertTrue(error.getFormattedMessage().startsWith("Scrittura MQ"));
            assertTrue(error.getFormattedMessage().contains("esito negativo"));
            assertTrue(error.getFormattedMessage().contains(QUEUE));
            assertTrue(error.getFormattedMessage().contains(XML));
            assertNotNull(error.getThrowableProxy());
        }
    }
}
