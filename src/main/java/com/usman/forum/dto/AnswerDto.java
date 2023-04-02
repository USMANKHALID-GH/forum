package com.usman.forum.dto;



import com.usman.forum.model.SubAnswers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto extends BaseDto {

    private String content;
    private String image;
    private LocalDateTime createdDate;
    private List<SubAnswerDto> subAnswers;
    private  Integer likeCount;
}
