package com.music.musicStreaming.controller;


import com.music.musicStreaming.models.Dtos.SignInInput;
import com.music.musicStreaming.models.Dtos.SignUpOutput;
import com.music.musicStreaming.models.Music;
import com.music.musicStreaming.models.User;
import com.music.musicStreaming.service.TokenService;
import com.music.musicStreaming.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping("User/Signup")
    public SignUpOutput signUp(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.signUp(user);
    }

    @PostMapping("User/SignIn")
    public String signIn(@RequestBody @Valid SignInInput signInInput) throws NoSuchAlgorithmException {
        return userService.signIn(signInInput);
    }

    @GetMapping("PlayList/{id}")
    public List<Music> getPlayList(@RequestParam Long id)throws NoSuchAlgorithmException{
        return userService.getPlayList(id);
    }
    @DeleteMapping("Music/{ID}/{musicId}")
    public  String deleteMusic (@RequestParam Long id , Long musicId)throws NoSuchAlgorithmException{
        return userService.deleteMusic(id ,musicId);
    }
    @DeleteMapping("user/SignOut")
    public  String signOut (@RequestParam String email, @RequestParam String token){
        if(tokenService.authenticate(email,token)){
            return userService.logOut(email);
        }
        return "Authentication Failed";
    }

}