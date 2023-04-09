package com.usman.forum.dto;

import lombok.Data;

@Data
public class UserAuthenticateRequestDto extends  BaseDto{
    private String email;
    private String password;
}
