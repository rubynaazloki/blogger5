package com.blogger5.service.impl;

import com.blogger5.entity.Post;
import com.blogger5.exception.ResourceNotFoundException;
import com.blogger5.payload.PostDto;
import com.blogger5.payload.PostResponse;
import com.blogger5.repository.PostRepository;
import com.blogger5.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }




    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);

        Post savePost = postRepository.save(post);

        PostDto dto = mapToDto(savePost);
        return dto;
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);

    }

    @Override
    public PostDto UpdatePost(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id" + id)
        );
                post.setTitle(postDto.getTitle());
                post.setDescription(postDto.getDescription());
                post.setContent(postDto.getContent());

                Post updatePost = postRepository.save(post);

                PostDto dto= mapToDto(updatePost);
                return dto;

    }

    @Override
    public PostDto getpostbyId(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id" + id)
        );

        PostDto dto = mapToDto(post);
        return dto;
    }

   @Override
    public PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
       Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? // turnery operator can reduces no of lines and its also like if else condition
               Sort.by(sortBy).ascending():
               Sort.by(sortBy).descending();

       Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
       Page<Post>pagepost = postRepository.findAll(pageable);
       List<Post>posts = pagepost.getContent();
       List<PostDto> postDtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse =new PostResponse();
        postResponse.setPostDto(postDtos);
        postResponse.setPageNo(pagepost.getNumber());
        postResponse.setPageSize(pagepost.getSize());
        postResponse.setTotalElements(pagepost.getNumberOfElements());
        postResponse.setTotalPages(pagepost.getTotalPages());
        postResponse.setLast(pagepost.isLast());

        return postResponse;
    }


    PostDto mapToDto (Post post){
           PostDto dto = modelMapper.map(post, PostDto.class);
            //PostDto dto = new PostDto();
            //dto.setId(post.getId());
            //dto.setTitle(post.getTitle());
            //dto.setDescription(post.getDescription());
            //dto.setContent(post.getContent());
            return dto;
    }

    Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
        //Post post = new Post();
        //post.setTitle(postDto.getTitle());
        //post.setDescription(postDto.getDescription());
        //post.setContent(postDto.getContent());
        return post;
    }
}
