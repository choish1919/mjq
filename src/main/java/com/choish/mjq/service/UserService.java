package com.choish.mjq.service;

import com.choish.mjq.domain.emails.EmailsRepository;
import com.choish.mjq.exception.AlreadyExistsException;
import com.choish.mjq.exception.UnauthorizedException;
import com.choish.mjq.domain.users.UserRepository;
import com.choish.mjq.domain.users.Users;
import com.choish.mjq.dto.users.UsersCreateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private EmailsRepository emailsRepository;

    // 가입
    public Users register(UsersCreateRequestDto dto){
        Users users = userRepository.findByEmail(dto.getEmail());
        if(users != null)
            throw new AlreadyExistsException("이미 존재하는 이메일입니다.");
        if(!emailsRepository.findById(dto.getEmail()).isPresent())
            throw new UnauthorizedException("인증되지 않은 이메일입니다.");
        return userRepository.save(dto.toEntity());
    }

    // 모든 사용자 리스트를 반환
    public List<Users> userList(){
        return userRepository.findAllByOrderByIdDesc();
    }

    // 해당 ID의 사용자를 반환
    public Users findById(Long id){
        return userRepository.findById(id).get();
    }

    // 인증
    public Users authentication(String token){
        try {
            // authorization으로부터 type과 credential을 분리
            String[] split = token.split(" ");
            String type = split[0];
            String credential = split[1];
            Users users = null;

            if ("Basic".equalsIgnoreCase(type)) {
                // credential을 디코딩하여 email과 password를 분리
                String decoded = new String(Base64Utils.decodeFromString(credential));
                String[] emailAndPassword = decoded.split(":");

                users = userRepository.findByEmailAndPw(emailAndPassword[0], emailAndPassword[1]);
                if (users == null) throw new UnauthorizedException("아이디 또는 비밀번호가 일치하지 않습니다.");
                return users;
            } else {
                throw new UnauthorizedException("지원하지 않는 타입의 Authorization: " + type);
            }
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex){
            throw new UnauthorizedException("유효하지 않은 접근입니다.");
        }
    }

    //탈퇴
    public void withdraw(String token){
        Users users = this.authentication(token);
        userRepository.delete(users);
    }
}