package com.music.musicStreaming.repository;

import com.music.musicStreaming.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo  extends JpaRepository<Admin,Long> {

    Admin findFirstByAdminEmail(String adminEmail);
}