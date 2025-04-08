package ru.develgame.javaee.websockets.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import ru.develgame.javaee.websockets.dao.ChatMessageDao;
import ru.develgame.javaee.websockets.entity.ChatMessage;
import ru.develgame.javaee.websockets.jms.MessagesType;
import ru.develgame.javaee.websockets.jsf.model.ChatLazyModel;
import ru.develgame.javaee.websockets.user.UserBean;
import ru.develgame.javaee.websockets.jms.ChangesInformer;
import ru.develgame.javaee.websockets.jms.ChangesListener;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.develgame.javaee.websockets.jsf.model.ChatLazyModel.CHAT_PAGE_LIMIT;

@Named("chatRoom")
@SessionScoped
public class ChatRoomBean implements Serializable, MessageListener {
    @Inject
    private transient ChatMessageDao chatMessageDao;

    @Inject
    private ChatLazyModel chatLazyModel;

    @Inject
    private UserBean userBean;

    @Inject
    private transient ChangesInformer changesInformer;

    @Inject
    private transient ChangesListener changesListener;

    @Inject
    private transient Logger logger;

    @Inject
    @Push(channel = "chatRoomChannel")
    private PushContext chatRoomChannel;

    private String chatMessage;

    private int chatPageLimit = CHAT_PAGE_LIMIT;

    @PostConstruct
    public void init() {
        changesListener.addListener(this);
    }

    @PreDestroy
    public void finite() {
        changesListener.removeListener(this);
    }

    public void addMessage() {
        if (chatMessage == null || chatMessage.isEmpty()) {
            return;
        }

        if (chatMessageDao.addChatMessage(new ChatMessage(userBean.getUsername(), chatMessage))) {
            changesInformer.sendMessage(MessagesType.CHAT);
        }

        chatMessage = "";
    }

    public boolean isOwnMessage(String username) {
        return userBean.getUsername().equals(username);
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public ChatLazyModel getChatLazyModel() {
        return chatLazyModel;
    }

    public int getChatPageLimit() {
        return chatPageLimit;
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (((TextMessage) message).getText().equals(MessagesType.CHAT.toString())) {
                chatRoomChannel.send("update");
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Cannot get JMS message", e);
        }
    }

    private static final long serialVersionUID = -4280928171896927886L;
}
