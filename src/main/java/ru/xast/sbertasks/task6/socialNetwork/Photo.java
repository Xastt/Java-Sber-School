package ru.xast.sbertasks.task6.socialNetwork;

import lombok.*;

@Getter
@Setter
public class Photo {
    private String id;
    private String userId;
    private int[] data;

    public Photo(String id, String userId, int[] data) {
        this.id = id;
        this.userId = userId;
        this.data = data;
    }
}
