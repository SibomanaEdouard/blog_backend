package com.test.qTestApp.Dto;



import com.test.qTestApp.Models.User;
import lombok.Data;

import java.util.UUID;

@Data
public class PostResponseDto {
    private UUID id;
    private String title;
    private String content;
    private String username;
}

