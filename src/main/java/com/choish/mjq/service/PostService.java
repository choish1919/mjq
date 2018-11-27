package com.choish.mjq.service;

import com.choish.mjq.domain.posts.Posts;
import com.choish.mjq.domain.posts.PostsRepository;
import com.choish.mjq.domain.posts.PostsSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostsRepository postsRepository;

    @Autowired
    public PostService(PostsRepository postsRepository){
        this.postsRepository = postsRepository;
    }

    // 작성
    public Posts write(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity());
    }

    // 모든 게시물 리스트를 반환
    public List<Posts> postList(){
        return postsRepository.findAll();
    }

    // 해당 ID의 게시물을 반환
    public Posts findById(Long id){
        return postsRepository.findById(id).get();
    }

    // 삭제
    public void deleteById(Long id){
        postsRepository.deleteById(id);
    }
}
