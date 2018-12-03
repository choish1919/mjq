package com.choish.mjq.dto.applicants;

import com.choish.mjq.domain.applicants.Applicants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicantsCreateRequestDto {
    private Long postid;
    private Long applicantsid;
    private Long authorid;

    @Builder
    public ApplicantsCreateRequestDto(Long postid, Long applicantsid, Long authorid){
        this.postid = postid;
        this. applicantsid = applicantsid;
        this. authorid = authorid;
    }

    public Applicants toEntity() {
        return Applicants.builder()
                .postid(postid)
                .applicantsid(applicantsid)
                .authorid(authorid)
                .build();
    }
}
