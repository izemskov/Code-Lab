package ru.develgame.javaee.websockets.dao;

import java.util.List;
import ru.develgame.javaee.websockets.entity.ChatMessage;

public interface ChatMessageDao {
    List<ChatMessage> getChatMessages(int offset, int pageSize);

    ChatMessage getLastMessage();

    Integer getAllMessagesCount();

    boolean addChatMessage(ChatMessage chatMessage);
}
