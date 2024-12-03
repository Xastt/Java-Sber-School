package ru.xast.sbertasks.task6.socialNetwork;

import java.util.List;

public interface PostService {
    void createPost(String userId, String content);
    List<Post> getAllPosts(String userId);
}
