package uz.pdp.appnews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appnews.payload.ApiResponse;
import uz.pdp.appnews.payload.RegisterDto;
import uz.pdp.appnews.payload.UserDto;
import uz.pdp.appnews.service.AuthService;
import uz.pdp.appnews.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService service;

//    @PostMapping("/register")
//    public HttpEntity<?> registerUser(@Valid @RequestBody UserDto dto){
//        ApiResponse apiResponse = service.addUser(dto);
//        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
//    }
}
