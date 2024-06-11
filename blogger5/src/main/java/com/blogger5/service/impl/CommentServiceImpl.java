package com.blogger5.service.impl;

import com.blogger5.entity.Comment;
import com.blogger5.entity.Post;
import com.blogger5.exception.ResourceNotFoundException;
import com.blogger5.payload.CommentDto;
import com.blogger5.repository.CommentRepository;
import com.blogger5.repository.PostRepository;
import com.blogger5.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId). orElseThrow(
                ()-> new ResourceNotFoundException("post not found with Id:"+postId)
        );

        comment.setPost(post);

        Comment saveComment = commentRepository.save(comment);

        CommentDto dto = mapToDto(saveComment);
        return dto;

    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId) .orElseThrow(
         ()-> new ResourceNotFoundException("post not found with id:" +postId)
        );

        List<Comment> comments = commentRepository.findBypostId(postId);

        List<CommentDto> commentDtos = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto getCommentsById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("post not found with Id:" +postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("comment not found with Id:" +commentId)
        );

       CommentDto commentDto = mapToDto(comment);
        return commentDto;
    }

    @Override
    public List<CommentDto> getAllCommentsById() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public String deleteCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("post not found with Id:" +postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("comment not found with Id:" +commentId)
        );

          commentRepository.deleteById(commentId);

        return ("Comment with ID " + commentId + " deleted successfully");
    }

    private CommentDto mapToDto(Comment saveComment) {
    CommentDto dto = mapper.map(saveComment, CommentDto.class);
    return  dto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
    Comment comment = mapper.map(commentDto, Comment.class);
    return comment;
    }


}


//getPostById
//getCommentsByPostId  List<CommentDto>
//getCommentById
//getAllCommentsById
//deleteCommentById

