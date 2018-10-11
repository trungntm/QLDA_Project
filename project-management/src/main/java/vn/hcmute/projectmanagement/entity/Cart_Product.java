package vn.hcmute.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="cart_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart_Product {
    @EmbeddedId
    private Cart_Product_Id id;

    private Long quantity;
}
