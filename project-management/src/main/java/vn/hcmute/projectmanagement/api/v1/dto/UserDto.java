package vn.hcmute.projectmanagement.api.v1.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String username;
    private String password;
}
