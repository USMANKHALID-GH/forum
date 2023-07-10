package com.usman.forum.dto;

import com.usman.forum.model.Role;
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
public class UserDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String relatedField;
    private  String email;
    private String  phoneNumber;
    private LocalDateTime createdDate;
    private List<Role> roles;
}
