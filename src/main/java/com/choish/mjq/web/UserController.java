package com.choish.mjq.web;

import com.choish.mjq.domain.users.Users;
import com.choish.mjq.domain.users.UserRepository;
import com.choish.mjq.domain.users.UsersCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // CREATE
    // 사용자 이메일을 입력받아 새로운 User를 생성하고 그 결과를 반환
    @PostMapping("/create")
    public Users createUser(@RequestBody UsersCreateRequestDto dto){
        return userRepository.save(dto.toEntity());
    }

    // READ
    // 모든 사용자 리스트를 반환
    @GetMapping
    public Iterable<Users> list(){
        return userRepository.findAll();
    }

    // READ
    // 해당 ID의 사용자를 반환
    @GetMapping(value = "/{id}")
    public Users findById(@PathVariable Long id){
        return userRepository.findById(id).get();
    }

    // UPDATE
    // 해당 ID의 사용자 이름을 갱신한 뒤 그 결과를 반환
    @PutMapping(value = "/{id}")
    public Users update(@PathVariable Long id, @RequestParam String nickname){
        Users user = userRepository.findById(id).get();
        return userRepository.save(user);
    }

    // DELETE
    // 해당 ID의 사용자를 삭제
    @DeleteMapping
    public void delete(@RequestParam Long id) {
        userRepository.deleteById(id);
    }
}
