package com.test.qTestApp.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MyPostResponse {
    private UUID id;
    private String content;
    private String title;
    private UUID authorId;
    private String username;
}
