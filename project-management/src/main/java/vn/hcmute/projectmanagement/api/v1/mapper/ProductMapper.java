package vn.hcmute.projectmanagement.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.hcmute.projectmanagement.api.v1.dto.ProductDto;
import vn.hcmute.projectmanagement.entity.Product;
@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product user);
}
