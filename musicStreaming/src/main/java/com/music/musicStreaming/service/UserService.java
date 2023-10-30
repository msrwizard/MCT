package com.music.musicStreaming.service;


import com.music.musicStreaming.models.AuthenticationToken;
import com.music.musicStreaming.models.Dtos.SignInInput;
import com.music.musicStreaming.models.Dtos.SignUpOutput;
import com.music.musicStreaming.models.Music;
import com.music.musicStreaming.models.User;
import com.music.musicStreaming.repository.IMusicRepo;
import com.music.musicStreaming.repository.ITokenRepo;
import com.music.musicStreaming.repository.IUserRepo;
import com.music.musicStreaming.service.utility.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    ITokenRepo tokenRepo;
    @Autowired
    IMusicRepo musicRepo;

    public SignUpOutput signUp(User user)throws NoSuchAlgorithmException {

        String userEmail = user.getUserEmail();

        User user1 = userRepo.findFirstByUserEmail(userEmail);

        if(user1 != null){
            return new SignUpOutput(false ,"Email Already registered....!!!");
        }
        String hexPassWord = Encrypt.encryptPassword(user.getUserPassword());
        user.setUserPassword(hexPassWord);

        userRepo.save(user);
        return new SignUpOutput(true ,"Sign Up SuccessFull....!!!");
    }

    public String signIn(SignInInput signInInput) throws NoSuchAlgorithmException {

        String email = signInInput.getEmail();

        String password = signInInput.getPassword();

        if(email == null) {
            return "Invalid credentials...!!!";
        }
        User user1 = userRepo.findFirstByUserEmail(email);

        if(user1 == null) return "Email not exist sign Up first...!!!";
        if(!Encrypt.encryptPassword(password).equals(user1.getUserPassword()))return "Invalid Sign In credentials...!!";

        AuthenticationToken authTokenUser = new AuthenticationToken(user1);
        tokenRepo.save(authTokenUser);

        return "Signed In Successfully...!!! your token is " + authTokenUser.getTokenValue();
    }

    public String logOut(String email) {
        User user = userRepo.findFirstByUserEmail(email);

        AuthenticationToken authToken = tokenRepo.findFirstByUser(user);
        tokenRepo.delete(authToken);

        return "Logged Out.....!!!!";
    }

    public List<Music> getPlayList(Long id)throws NoSuchAlgorithmException {
        List<Music> musicList = new ArrayList<>();

        User musicUser = userRepo.findById(id).get();
        List<Long> arrayList = musicUser.getPlayList();

        for(int idx = 0 ; idx < arrayList.size() ; idx++) {

            Music music= musicRepo.findById(arrayList.get(idx)).get();
            musicList.add(music);
        }
        return musicList;
    }

    public String deleteMusic(Long id, Long musicId) throws NoSuchAlgorithmException{
        User musicUser = userRepo.findById(id).get();
        List<Long> arrayList=musicUser.getPlayList()
                ;
        for(int jdx = 0 ; jdx < arrayList.size() ; jdx++) {

            Long id1 = arrayList.get(jdx);
            if(id1.equals( musicId)) {
                arrayList.remove(jdx);
            }
        }
        return "music deleted form playlist";
    }


}