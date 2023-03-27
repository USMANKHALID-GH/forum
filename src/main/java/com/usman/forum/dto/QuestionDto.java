package com.usman.forum.dto;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto extends  BaseDto {

    private String questionTitle;
    private String questions;
    private String image;


}
