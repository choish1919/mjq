package com.choish.mjq.service;

import com.choish.mjq.domain.posts.Posts;
import com.choish.mjq.domain.posts.PostsRepository;
import com.choish.mjq.dto.posts.PostsSaveRequestDto;
import com.choish.mjq.dto.posts.PostsUpdateRequestDto;
import com.choish.mjq.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.List;

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
        return postsRepository.findAllByOrderByIdDesc();
    }

    // 해당 ID의 게시물을 반환
    public Posts findById(Long id){
        return postsRepository.findById(id).get();
    }

    // 인증
    public void authentication(Long id, String credential){
        try {
            Posts posts = null;

            // credential을 디코딩하여 author가 누군지 파악
            String decoded = new String(Base64Utils.decodeFromString(credential));
            Long authorId = Long.parseLong(decoded);

            posts = postsRepository.findById(id).get();

            if (posts.getAuthorid() != authorId) throw new UnauthorizedException("Invalid credential");

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex){
            throw new UnauthorizedException("Invalid credentials");
        }
    }

    // 삭제
    public void deleteById(Long id, String credential){
        authentication(id, credential);
        postsRepository.deleteById(id);
    }

    // 게시물 수정
    public Posts update(Long id, String credential, PostsUpdateRequestDto dto) {
        authentication(id, credential);
        Posts posts = postsRepository.getOne(id);
        if(dto.getTitle() != null) posts.setTitle(dto.getTitle());
        if(dto.getContent() != null) posts.setContent(dto.getContent());
        if(dto.getReward() != null) posts.setReward(dto.getReward());
        if(dto.getModified_date() != null) posts.setModified_date(dto.getModified_date());
        return postsRepository.save(posts);
    }
}
