package com.choish.mjq.dto.notices;

import com.choish.mjq.domain.notices.Notices;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticesCreateRequestDto {
    private String title;
    private String content;
    private String created_date;

    @Builder
    public NoticesCreateRequestDto(String title, String content, String created_date){
        this.title = title;
        this.content = content;
        this.created_date = created_date;
    }

    public Notices toEntity() {
        return Notices.builder()
                .title(title)
                .content(content)
                .created_date(created_date)
                .build();
    }
}
