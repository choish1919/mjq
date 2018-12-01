package com.choish.mjq.controller;

import com.choish.mjq.domain.emails.Emails;
import com.choish.mjq.domain.emails.EmailsRepository;
import com.choish.mjq.domain.users.UserRepository;
import com.choish.mjq.domain.users.Users;
import com.choish.mjq.dto.emails.EmailsSaveRequestDto;
import com.choish.mjq.dto.users.UsersCreateRequestDto;
import com.choish.mjq.exception.AlreadyExistsException;
import com.choish.mjq.exception.UnauthorizedException;
import com.choish.mjq.service.MailService;
import com.choish.mjq.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;
    private MailService mailService;

    private EmailsRepository emailsRepository;
    private UserRepository userRepository;

    // 사용자 이메일을 입력받아 새로운 User를 생성하고 그 결과를 반환
    @PostMapping("/register")
    public Users register(@RequestBody UsersCreateRequestDto dto){
        Users users = userRepository.findByEmail(dto.getEmail());
        if(users != null)
            throw new AlreadyExistsException("이미 존재하는 이메일입니다.");
        if(!emailsRepository.findById(dto.getEmail()).isPresent())
            throw new UnauthorizedException("인증되지 않은 이메일입니다.");
        return userService.register(dto);
    }

    // 계정 인증 메일 보내기
    @PostMapping("/register/send")
    public Emails registerMail(@RequestBody EmailsSaveRequestDto dto){
        if(!dto.getEmail().contains("@"))  throw new UnauthorizedException("이메일 형식이 아닙니다.");
        String domain = dto.getEmail().split("@")[1];
        if(!domain.equals("mju.ac.kr") && !domain.equals("gmail.com"))
            throw new UnauthorizedException("유효하지 않은 도메인입니다.");
        if(emailsRepository.findByEmail(dto.getEmail()) != null)
            throw new AlreadyExistsException("이미 인증된 이메일입니다.");
        return mailService.sendEmail(dto);
    }

    // 계정 인증 메일 인증
    @GetMapping(value = "/register/{token}")
    public String registerMailAuth(@PathVariable String token){
        try {
            // 5분 테스트용을 30초
            long expire = 30000;
            String decoded = new String(Base64Utils.decodeFromString(token));
            String domain = (decoded.split("@")[1]).split(":")[0];
            String email = decoded.split(":")[0];
            long timestamp = Long.parseLong((decoded.split("@")[1]).split(":")[1]);
            long elapsed = new Date().getTime() - timestamp;
            if(!domain.equals("mju.ac.kr") && !domain.equals("gmail.com")) {
                return "유효하지 않은 도메인입니다.";
                //throw new UnauthorizedException("유효하지 않은 도메인입니다.");
            }
            if(emailsRepository.findById(email).isPresent()) {
                return "이미 인증 받은 이메일입니다.\n가입을 진행해주세요.";
                //throw new UnauthorizedException("이미 인증 받은 이메일입니다. 가입을 진행해주세요.");
            }
            // 시간이 경과하면
            if(elapsed > expire) {
                return "토큰이 만료되었습니다.\n5분 이내에 인증을 완료하여야 합니다.\n다시 인증 메일을 발송해주세요.\n\n경과 시간: "+ (elapsed/1000) / 60 + "분 " + ((elapsed/1000)%60) + "초";
                //throw new UnauthorizedException("인증 시간이 만료되었습니다. 5분 이내에 인증을 완료하여야 합니다. 다시 인증 메일을 발송해주세요.");
            }
            emailsRepository.save(Emails.builder().email(email).build());
            return "이메일 인증이 완료되었습니다. 인증 받은 이메일로 가입을 진행해주세요.";
        } catch(IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            throw new UnauthorizedException("유효하지 않은 토큰입니다.");
        }
    }

    // 로그인 인증
    @PostMapping("/login")
    public Users login(@RequestHeader String authorization){
        return userService.authentication(authorization);
    }

    // 모든 사용자 리스트를 반환
    @GetMapping
    public Iterable<Users> userList(){
        return userService.userList();
    }

    // 해당 ID의 사용자를 반환
    @GetMapping(value = "/{id}")
    public Users findById(@PathVariable Long id){
        return userService.findById(id);
    }

    // 정보 수정
    //@PutMapping
    //public Users update(@RequestHeader String authorization) { return userService.update }

    // 해당 ID의 사용자를 삭제
    @DeleteMapping
    public void quit(@RequestHeader String authorization){
        userService.withdraw(authorization);
    }
}