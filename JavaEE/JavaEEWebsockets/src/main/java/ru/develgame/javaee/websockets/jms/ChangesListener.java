package ru.develgame.javaee.websockets.jms;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ApplicationScoped
public class ChangesListener {
    @Resource(lookup = "jms/JSFChatConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/JSFChatTopic")
    private Topic topic;

    @Inject
    private Logger logger;

    private Connection connection;
    private Session session;

    private List<MessageConsumer> consumers = new LinkedList<>();

    @PostConstruct
    public void init() {
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            connection.start();
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Cannot get JMS message", e);
        }
    }

    @PreDestroy
    public void finite() {
        try {
            for (MessageConsumer consumer : consumers) {
                consumer.close();
            }

            session.close();
            connection.close();
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Cannot get JMS message", e);
        }
    }

    public void addListener(MessageListener messageListener) {
        try {
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(messageListener);
            consumers.add(consumer);
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Cannot get JMS message", e);
        }
    }

    public void removeListener(MessageListener messageListener) {
        try {
            Iterator<MessageConsumer> iterator = consumers.iterator();
            while (iterator.hasNext()) {
                MessageConsumer next = iterator.next();
                if (next.getMessageListener().equals(messageListener)) {
                    iterator.remove();
                }
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Cannot get JMS message", e);
        }
    }
}
