package com.blogger5.conroller;

import com.blogger5.payload.PostDto;
import com.blogger5.payload.PostResponse;
import com.blogger5.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) { //instead of @Autowired later updates of Spring Dependency injection can be constructor base
        this.postService = postService;
    }

    //http://localhost:8080/api/post
    @PostMapping
    public ResponseEntity<?> saveDto(@Valid @RequestBody PostDto postDto, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError(). getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201

    }

    //http:localhost:8080/api/post/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("Id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK); //200
    }

    //http:localhost:8080/api/post/1
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("Id") long id, @RequestBody PostDto postDto) {
        PostDto dto = postService.UpdatePost(id, postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK); //200;
    }

    //http:localhost:8080/api/post/1
    public ResponseEntity<PostDto> getpostbyId(@PathVariable("id") long id) {
        PostDto dto = postService.getpostbyId(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
    //http:localhost:8080/api/post/pageNo=0&Pagesize=5&sortBy=title
    @GetMapping
    public PostResponse getPosts(
       @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
       @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
       @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
       @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir

    ){
        PostResponse postResponse = postService.getPosts(pageNo,pageSize, sortBy, sortDir);
        return postResponse;
}

}