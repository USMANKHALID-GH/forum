package com.usman.forum.dto;




import com.usman.forum.model.Answers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto extends  BaseDto {

    private String title;
    private String content;
    private String image;
//    private List<Answers> answers;


}
