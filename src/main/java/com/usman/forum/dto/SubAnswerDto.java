package com.usman.forum.dto;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubAnswerDto extends  BaseDto {
    private String content;
    private String image;

    private LocalDateTime createdDate;
    private  Integer likeCount;

}
