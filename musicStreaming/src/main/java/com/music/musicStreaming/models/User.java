package com.music.musicStreaming.models;


import com.music.musicStreaming.models.Enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    private String userName;

    @NotNull
    private Integer userAge;

    @Email
    @Column(unique = true)
    private String userEmail;

    @NotBlank
    private String userPassword;

    @Column(name = "playList")
    private List<Long> playList;

    @Enumerated(EnumType.STRING)
    private Gender gender;


}