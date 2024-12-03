package ru.xast.sbertasks.task6.socialNetwork;

import lombok.*;

@Getter
@Setter
public class Post {
    private String id;
    private String title;
    private String content;
    private String userId;

    public Post(String id, String title, String content, String userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
