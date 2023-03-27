package com.usman.forum.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto extends BaseDto {

    private String answers;
    private String image;
}
