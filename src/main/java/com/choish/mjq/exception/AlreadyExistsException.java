package com.choish.mjq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 이미 존재
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already exists")
public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message){
        super(message);
    }
}
