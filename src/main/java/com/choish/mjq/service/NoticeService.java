package com.choish.mjq.service;

import com.choish.mjq.domain.notices.Notices;
import com.choish.mjq.domain.notices.NoticesRepository;
import com.choish.mjq.dto.notices.NoticesCreateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoticeService {
    private NoticesRepository noticesRepository;

    // 공지사항 작성
    public Notices noticeWrite(NoticesCreateRequestDto dto) { return noticesRepository.save(dto.toEntity());}

    // 모든 공지사항 리스트를 반환
    public Iterable<Notices> noticeList(){
        return noticesRepository.findAllByOrderByIdDesc();
    }

    // 해당 페이지 반환
    public Iterable<Notices> noticePage(PageRequest pageRequest){
        Page<Notices> noticesPage = noticesRepository.findAllByOrderByIdDesc(pageRequest);
        return noticesPage.getContent();
    }

    // 해당 ID의 공지사항을 반환
    public Notices findNoticeById(Long id){
        return noticesRepository.findById(id).get();
    }
}