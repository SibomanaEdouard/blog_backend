package com.test.qTestApp.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentResponseDto {
    private UUID id;
    private String content;
    private String username;
    private UUID authorId;
}
