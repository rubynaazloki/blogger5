package com.blogger5.payload;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String email;
    private String name;
    private String body;
}