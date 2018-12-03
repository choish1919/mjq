package com.choish.mjq.service;

import com.choish.mjq.domain.applicants.Applicants;
import com.choish.mjq.domain.applicants.ApplicantsRepository;
import com.choish.mjq.domain.posts.Posts;
import com.choish.mjq.domain.posts.PostsRepository;
import com.choish.mjq.dto.applicants.ApplicantsCreateRequestDto;
import com.choish.mjq.dto.posts.PostsCreateRequestDto;
import com.choish.mjq.dto.posts.PostsUpdateRequestDto;
import com.choish.mjq.exception.UnauthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
@AllArgsConstructor
public class PostService {
    private PostsRepository postsRepository;
    private ApplicantsRepository applicantsRepository;

    // 게시물 작성
    public Posts postWrite(PostsCreateRequestDto dto){
        return postsRepository.save(dto.toEntity());
    }

    // 모집 신청
    public Applicants apply(ApplicantsCreateRequestDto dto){
        return applicantsRepository.save(dto.toEntity());
    }

    // 모든 게시물 리스트를 반환
    public Iterable<Posts> postList(){
        return postsRepository.findAllByOrderByIdDesc();
    }

    // 해당 페이지 반환
    public Iterable<Posts> postPage(PageRequest pageRequest){
        Page<Posts> postsPage = postsRepository.findAllByOrderByIdDesc(pageRequest);
        return postsPage.getContent();
    }

    // 해당 ID의 게시물을 반환
    public Posts findPostById(Long id){
        return postsRepository.findById(id).get();
    }

    // 해당 ID의 게시물의 Applicants List를 반환
    public Iterable<Applicants> applicantsList(Long id) { return applicantsRepository.findAllByPostid(id); }

    // 인증
    public void authentication(Long id, String credential){
        try {
            Posts posts = null;

            // credential을 디코딩하여 author가 누군지 파악
            String decoded = new String(Base64Utils.decodeFromString(credential));
            Long authorId = Long.parseLong(decoded);

            posts = postsRepository.findById(id).get();
            if (posts.getAuthorid() != authorId) throw new UnauthorizedException("작성자만 수정 및 삭제가 가능합니다.");
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex){
            throw new UnauthorizedException("유효하지 않은 접근입니다.");
        }
    }

    // 게시물 삭제
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