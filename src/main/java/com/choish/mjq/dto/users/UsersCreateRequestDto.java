package com.choish.mjq.dto.users;

import com.choish.mjq.domain.posts.Posts;
import com.choish.mjq.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersCreateRequestDto {
    private String email;
    private String pw;
    private String nickname;
    private Long exp;
    private Long lv;

    @Builder
    public UsersCreateRequestDto(String email, String pw, String nickname, Long exp, Long lv){
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.exp = exp;
        this.lv = lv;
    }

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .pw(pw)
                .nickname(nickname)
                .exp(new Long(0))
                .lv(new Long(1))
                .build();
    }
}
