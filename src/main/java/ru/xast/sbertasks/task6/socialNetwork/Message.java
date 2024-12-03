package ru.xast.sbertasks.task6.socialNetwork;


import lombok.*;

@Getter
@Setter
public class Message {
    private String id;
    private String senderId;
    private String recipientId;
    private String content;

    public Message(String id, String senderId, String recipientId, String content) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }
}
