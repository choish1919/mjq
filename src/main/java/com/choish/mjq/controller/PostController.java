package com.choish.mjq.controller;

import com.choish.mjq.domain.posts.Posts;
import com.choish.mjq.dto.posts.PostsCreateRequestDto;
import com.choish.mjq.dto.posts.PostsUpdateRequestDto;
import com.choish.mjq.exception.UnauthorizedException;
import com.choish.mjq.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {
    private PostService postService;

    // 게시물 작성
    @PostMapping
    public Posts write(@RequestBody PostsCreateRequestDto dto){
        if(dto.getAuthor() == null || dto.getContent() == null || dto.getTitle() == null || dto.getReward() == null || dto.getAuthorid() == null || dto.getCreated_date() == null)
            throw new UnauthorizedException("DTO가 유효하지 않습니다. 다음 중 NULL 값이 존재하면 안됩니다.: " + dto.toString());
        return postService.postWrite(dto);
    }

    // 모든 게시물 리스트를 반환
    @GetMapping("/all")
    public Iterable<Posts> postList(){
        return postService.postList();
    }

    // 해당 페이지, size 만큼 반환
    @GetMapping
    public List<Posts> postPage(@RequestParam int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return postService.postPage(pageRequest);
    }

    // 해당 ID의 게시물을 반환
    @GetMapping(value = "/{id}")
    public Posts findById(@PathVariable Long id){
        return postService.findPostById(id);
    }

    // 해당 게시물 수정
    @PutMapping(value = "/{id}")
    public Posts update(@PathVariable Long id, @RequestHeader String authorization, @RequestBody PostsUpdateRequestDto dto){
        return postService.update(id, authorization, dto);
    }

    // 해당 게시물을 삭제
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id, @RequestHeader String authorization){
        postService.deleteById(id, authorization);
    }
}