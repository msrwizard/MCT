package com.music.musicStreaming.service;


import com.music.musicStreaming.models.AuthenticationToken;
import com.music.musicStreaming.repository.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    ITokenRepo tokenRepo;

    public boolean authenticate(String email, String tokenValue)
    {
        AuthenticationToken authToken = tokenRepo.findFirstByTokenValue(tokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenEmail = authToken.getUser().getUserEmail();

        return tokenEmail.equals(email);
    }

    public boolean authenticateAdmin(String email, String tokenValue)
    {
        AuthenticationToken authToken = tokenRepo.findFirstByTokenValue(tokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenEmail = authToken.getAdmin().getAdminEmail();

        return tokenEmail.equals(email);
    }
}