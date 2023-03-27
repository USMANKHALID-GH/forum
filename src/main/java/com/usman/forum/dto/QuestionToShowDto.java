package com.usman.forum.dto;

import com.usman.forum.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionToShowDto extends BaseDto{

    private String questionTitle;
    private String questions;
    private String image;
    private User user;
    private LocalDateTime createdDate;
}
