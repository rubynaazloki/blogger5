package com.blogger5.service.impl;

import com.blogger5.entity.Post;
import com.blogger5.payload.PostDto;
import com.blogger5.repository.PostRepository;
import com.blogger5.service.PostService;

public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savePost = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setId(savePost.getId());
        dto.setTitle(savePost.getTitle());
        dto.setDescription(savePost.getDescription());
        dto.setContent(savePost.getContent());

        return dto;
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);

    }

}
