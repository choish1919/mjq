package com.choish.mjq.dto.matchings;

import com.choish.mjq.domain.matchings.Matchings;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchingsSaveRequestDto {
    private Long authorid;
    private Long applicantsid;
    private Long postid;

    @Builder
    public MatchingsSaveRequestDto(Long authorid, Long applicantsid, Long postid){
        this.authorid = authorid;
        this. applicantsid = applicantsid;
        this.postid = postid;
    }

    public Matchings toEntity() {
        return Matchings.builder()
                .authorid(authorid)
                .applicantsid(applicantsid)
                .postid(postid)
                .build();
    }
}
