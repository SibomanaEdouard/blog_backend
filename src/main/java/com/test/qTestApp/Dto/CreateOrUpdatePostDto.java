package com.test.qTestApp.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CreateOrUpdatePostDto {
    private String title;
    private String content;

}
