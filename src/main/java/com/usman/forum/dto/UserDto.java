package com.usman.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
    private String firstName;
    private String secondName;
    private String field;
    private  String email;
    private String  number;
}
