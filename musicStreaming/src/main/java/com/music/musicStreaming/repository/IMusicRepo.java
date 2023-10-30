package com.music.musicStreaming.repository;

import com.music.musicStreaming.models.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMusicRepo extends JpaRepository<Music,Long> {

}