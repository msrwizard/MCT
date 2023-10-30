package com.music.musicStreaming.controller;

import com.music.musicStreaming.models.Admin;
import com.music.musicStreaming.models.Dtos.SignInInput;
import com.music.musicStreaming.models.Dtos.SignUpOutput;
import com.music.musicStreaming.models.Music;
import com.music.musicStreaming.service.AdminService;
import com.music.musicStreaming.service.MusicService;
import com.music.musicStreaming.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Validated
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MusicService musicService;

    @PostMapping("Admin/Signup")
    public SignUpOutput signUp(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        return adminService.signUp(admin);
    }

    @PostMapping("Admin/SignIn")
    public String signIn(@RequestBody @Valid SignInInput signInInput) throws NoSuchAlgorithmException {
        return adminService.signIn(signInInput);
    }

    @DeleteMapping("Admin/SignOut")
    public  String signOut (@RequestParam String email, @RequestParam String token){
        if(tokenService.authenticateAdmin(email,token)){
            return adminService.logOut(email);
        }
        return "Authentication Failed";
    }

    @GetMapping("Music")
    public List<Music> getAllMusic(){
        return musicService.getAllMusic();
    }

    @PostMapping("Music")
    public String addMusic(@RequestBody @Valid Music music ) {
        return musicService.addMusic(music);

    }
    @PutMapping("Music/{id}/{artist}")
    public String updateMusicById(@RequestParam Long id ,@RequestParam String artist,@RequestParam String email, @RequestParam String token){
        if(tokenService.authenticate(email,token)){
            return musicService.updateMusicById(id ,artist , email);
        }
        return "Authentication Failed";

    }
    @DeleteMapping("Music/{id}")
    public String deleteMusicById(@RequestParam Long id){
        return musicService.deleteMusic(id);
    }
}