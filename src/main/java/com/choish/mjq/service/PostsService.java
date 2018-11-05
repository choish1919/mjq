package com.choish.mjq.service;

import com.choish.mjq.domain.posts.PostsRepository;
import com.choish.mjq.domain.posts.PostsSaveRequestDto;
import com.choish.mjq.dto.PostsMainResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional // Exception 발생 시 이전에 이루어진 DB 트랜잭션 롤백
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Object findAllDesc(){
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }
}
