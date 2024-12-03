package ru.xast.sbertasks.task6.socialNetwork;

import java.util.List;

public interface PhotoService {
    void uploadPhoto(String userId, byte[] photoData);
    List<Photo> getPhotos(String userId);
}
