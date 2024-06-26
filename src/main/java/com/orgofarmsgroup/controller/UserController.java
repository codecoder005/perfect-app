package com.orgofarmsgroup.controller;

import com.orgofarmsgroup.dto.response.ResponseDto;
import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<ResponseDto> getUsers(HttpServletRequest request) {
        try{
            List<UserEntity> users = userService.getUsers();
            ResponseDto responseDto = new ResponseDto(request, HttpStatus.OK, users);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }catch (Exception ex) {
            log.error("UserController.getUsers().Exception {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseDto(request, HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong."));
        }
    }
}
