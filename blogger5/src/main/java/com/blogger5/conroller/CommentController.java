

package com.blogger5.conroller;

import com.blogger5.payload.CommentDto;
import com.blogger5.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/api/")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //https://localhosts:8080/api/posts/{postId}/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId") long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    //https://localhosts:8080/api/posts/{postId}/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable (value ="postId") long postId){
       return commentService.getCommentsByPostId(postId);
    }
    //https://localhosts:8080/api/posts/{postId}/comments/1
    @GetMapping("/posts/{postId}/comments/{commentsId}")
    public CommentDto getCommentsById(@PathVariable(value="postId") long postId,
                                      @PathVariable(value="commentId") long commentId){
        return commentService.getCommentsById(postId,commentId);
    }

    //https://localhosts:8080/api/comments
    @GetMapping("/comments")
    public List<CommentDto> getAllCommentsById(){

        return commentService.getAllCommentsById();
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String>deleteCommentById(@PathVariable(value ="postId") long postId,
                                                    @PathVariable(value = "commentId") long commentId){
       commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("Comments is deleted",HttpStatus.OK);

    }
}











