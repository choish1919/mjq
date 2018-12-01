package com.choish.mjq.dto.emails;

import com.choish.mjq.domain.emails.Emails;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailsSaveRequestDto {
    private String email;

    @Builder
    public EmailsSaveRequestDto(String email){
        this.email = email;
    }

    public Emails toEntity() {
        return Emails.builder()
                .email(email)
                .build();
    }
}