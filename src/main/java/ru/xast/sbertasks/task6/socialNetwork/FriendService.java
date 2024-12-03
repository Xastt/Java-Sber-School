package ru.xast.sbertasks.task6.socialNetwork;

import java.util.List;

public interface FriendService {
    void addFriend(String userId, String friendId);
    void removeFriend(String userId, String friendId);
    List<User> getFriends(String userId);
}
