package com.choish.mjq.controller;

import com.choish.mjq.domain.notices.Notices;
import com.choish.mjq.dto.notices.NoticesCreateRequestDto;
import com.choish.mjq.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/notices")
public class NoticeController {

    private NoticeService noticeService;

    // 모든 공지사항 리스트를 반환
    @GetMapping
    public List<Notices> noticesList(){
        return noticeService.noticeList();
    }

    // 해당 번호의 공지사항 리스트 반환
    @GetMapping("id}")
    public Notices noticesList(@PathVariable Long id){
        return noticeService.findNoticeById(id);
    }

    // 공지사항 작성
    @PostMapping
    public Notices write(@RequestBody NoticesCreateRequestDto dto){
        return noticeService.noticeWrite(dto);
    }

    // 해당 게시물 수정
    //@PutMapping(value = "/{id}")
    //public Notices update(@PathVariable Long id, @RequestHeader String authorization, @RequestBody PostsUpdateRequestDto dto){
    //    return postService.update(id, authorization, dto);
    //}

    // 해당 공지사항 삭제
    //@DeleteMapping(value = "/{id}")
    //public void delete(@PathVariable Long id, @RequestHeader String authorization){
    //    postService.deleteById(id, authorization);
    //}
}