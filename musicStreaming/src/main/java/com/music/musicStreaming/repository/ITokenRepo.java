package com.music.musicStreaming.repository;

import com.music.musicStreaming.models.Admin;
import com.music.musicStreaming.models.AuthenticationToken;
import com.music.musicStreaming.models.Music;
import com.music.musicStreaming.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITokenRepo extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);

    AuthenticationToken findFirstByUser(User user);

    List<Music> findByUser(User user);

    AuthenticationToken findFirstByAdmin(Admin admin);
}