package vn.hcmute.projectmanagement.api.v1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonDto {
    private Long id;

    private String fullName;

    private Date dateOfBirth;

    private int sex;

    private String avatar;

    private String address;

    private String phone;

    private String email;
}
