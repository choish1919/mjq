package com.choish.mjq.web;

import com.choish.mjq.domain.users.Users;
import com.choish.mjq.domain.users.UserRepository;
import com.choish.mjq.dto.users.UsersCreateRequestDto;
import com.choish.mjq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // CREATE 가입
    // 사용자 이메일을 입력받아 새로운 User를 생성하고 그 결과를 반환
    @PostMapping("/join")
    public Users join(@RequestBody UsersCreateRequestDto dto){
        return userService.join(dto);
    }

    @PostMapping("/login")
    public Users login(@RequestHeader String authorization){
        return userService.authentication(authorization);
    }

    // READ
    // 모든 사용자 리스트를 반환
    @GetMapping
    public Iterable<Users> list(){
        return userService.userList();
    }

    // READ
    // 해당 ID의 사용자를 반환
    @GetMapping(value = "/{id}")
    public Users findById(@PathVariable Long id){
        return userService.findById(id);
    }

    // 자신의 정보를 반환
    @GetMapping(value = "/me")
    public Users getMe(@RequestHeader String authorization){
        return userService.authentication(authorization);
    }

    // DELETE
    // 해당 ID의 사용자를 삭제
    @DeleteMapping
    public void quit(@RequestHeader String authorization){
        userService.quit(authorization);
    }
}
