package ru.xast.sbertasks.task6.socialNetwork;

import java.util.List;

public interface MessageService {
    void sendMessage(String fromUserId, String toUserId, String message);
    List<Message> getMessages(String userId);
}
