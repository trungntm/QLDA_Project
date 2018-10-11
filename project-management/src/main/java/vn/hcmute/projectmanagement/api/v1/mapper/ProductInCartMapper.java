package vn.hcmute.projectmanagement.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import vn.hcmute.projectmanagement.api.v1.dto.ProductInCartDto;
import vn.hcmute.projectmanagement.entity.Cart_Product;

@Mapper
public interface ProductInCartMapper {
    ProductInCartMapper INSTANCE = Mappers.getMapper(ProductInCartMapper.class);

    @Mappings({
            @Mapping(source = "id.product.name", target = "name"),
            @Mapping(source = "id.product.images", target = "images"),
            @Mapping(source = "id.product.briefInfo", target = "briefInfo"),
            @Mapping(source = "id.product.price", target = "price")
    })
    ProductInCartDto cartProductToProductInCartDto(Cart_Product cartProduct);
}
