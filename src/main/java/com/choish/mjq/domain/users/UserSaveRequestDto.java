package com.choish.mjq.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequestDto {
    private Long id;
    private String email;
    private String pw;
    private String nickname;
    private Long exp;
    private Long lv;

    @Builder
    public UserSaveRequestDto(Long id, String email, String pw, String nickname, Long exp, Long lv){
        this.id = id;
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.exp = exp;
        this.lv = lv;
    }

    public Users toEntity() {
        return Users.builder()
                .id(id)
                .email(email)
                .pw(pw)
                .nickname(nickname)
                .exp(exp)
                .lv(lv)
                .build();
    }
}
