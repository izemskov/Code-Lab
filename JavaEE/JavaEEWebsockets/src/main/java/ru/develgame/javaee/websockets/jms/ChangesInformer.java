package ru.develgame.javaee.websockets.jms;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ApplicationScoped
public class ChangesInformer {
    @Resource(lookup = "jms/JSFChatConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/JSFChatTopic")
    private Topic topic;

    @Inject
    private Logger logger;

    public void sendMessage(MessagesType messagesType) {
        try {
            try (Connection connection = connectionFactory.createConnection()) {
                try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
                    try (MessageProducer producer = session.createProducer(topic)) {
                        TextMessage textMessage = session.createTextMessage();
                        textMessage.setText(messagesType.toString());

                        producer.send(textMessage);
                    }
                }
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Cannot send JMS message", e);
        }
    }
}
