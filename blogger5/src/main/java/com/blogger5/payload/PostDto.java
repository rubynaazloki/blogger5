package com.blogger5.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private Long id;
    @NotEmpty
    @Size (min=4, message= "Post tilte should not be less than 4 character")
   private String title;

    @NotEmpty
    @Size (min=8, message= "Post description should not be less than 8 character")
   private String description;

    @NotEmpty
    @Size (min=12, message= "Post content should not be less than 12 character")
    private String content;

}