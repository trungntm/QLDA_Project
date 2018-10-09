package vn.hcmute.projectmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterModel {
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String email;
    private int sex;
    private Date dob;
    private String address;
}
