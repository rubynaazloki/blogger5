package com.blogger5.service;

import com.blogger5.payload.PostDto;
import com.blogger5.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto savePost (PostDto postDto);

    void deletePost(long id);

    PostDto UpdatePost(long id, PostDto postDto);


    PostDto getpostbyId(long id);


    PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);


}
