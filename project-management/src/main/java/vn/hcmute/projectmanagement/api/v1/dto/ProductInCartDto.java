package vn.hcmute.projectmanagement.api.v1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductInCartDto {
    private String name;
    private String images;
    private String briefInfo;
    private Long price;
    private Long quantity;
}
