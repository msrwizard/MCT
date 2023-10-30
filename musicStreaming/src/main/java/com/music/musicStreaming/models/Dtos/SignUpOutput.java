package com.music.musicStreaming.models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpOutput {

    private boolean signUpStatus;
    private String signUpMessage;
}
