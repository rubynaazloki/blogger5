package com.blogger5.conroller;

import com.blogger5.payload.PostDto;
import com.blogger5.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) { //instead of @Autowired later updates of Spring Dependency injection can be constructor base
        this.postService = postService;
    }
    //http://localhost:8080/api/post
    @PostMapping
    public ResponseEntity<PostDto>saveDto(@RequestBody PostDto postDto){
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201
    }




    }

