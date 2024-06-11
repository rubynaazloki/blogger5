package com.blogger5.service;


import com.blogger5.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);


    CommentDto getCommentsById(long post, long comment);

    List<CommentDto> getAllCommentsById();

    String deleteCommentById(long postId, long commentId);
}

