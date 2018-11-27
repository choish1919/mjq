package com.choish.mjq.dto;

import com.choish.mjq.domain.users.Users;
import lombok.Getter;

@Getter
public class UserMainResponseDto {
    private Long id;
    private String email;
    private String pw;
    private String nickname;
    private Long exp;
    private Long lv;

    public UserMainResponseDto(Users entity){
        id = entity.getId();
        email = entity.getEmail();
        pw = entity.getPw();
        nickname = entity.getNickname();
        exp = entity.getExp();
        lv = entity.getLv();
    }
}
