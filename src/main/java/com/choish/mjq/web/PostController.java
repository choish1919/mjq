package com.choish.mjq.web;

import com.choish.mjq.domain.posts.Posts;
import com.choish.mjq.dto.posts.PostsSaveRequestDto;
import com.choish.mjq.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    // 작성
    @PostMapping("/write")
    public Posts write(@RequestBody PostsSaveRequestDto dto){
        return postService.write(dto);
    }

    // 모든 게시물 리스트를 반환
    @GetMapping
    public Iterable<Posts> postList(){
        return postService.postList();
    }

    // 해당 ID의 게시물을 반환
    @GetMapping(value = "/{id}")
    public Posts findById(@PathVariable Long id){
        return postService.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id, String credential){
        postService.deleteById(id, credential);
    }
}