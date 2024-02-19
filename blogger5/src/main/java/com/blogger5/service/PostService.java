package com.blogger5.service;

import com.blogger5.payload.PostDto;

public interface PostService {

    PostDto savePost (PostDto postDto);

    void deletePost(long id);
}
