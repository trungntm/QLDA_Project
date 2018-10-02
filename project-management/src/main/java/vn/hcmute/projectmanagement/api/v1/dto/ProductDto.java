package vn.hcmute.projectmanagement.api.v1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {
    private long id;
    private String name;
    private String usercreated;
    private Date datecreated;
    private String userupdated;
    private Date dateupdated;
}
