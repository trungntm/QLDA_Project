package vn.hcmute.projectmanagement.service;
import vn.hcmute.projectmanagement.entity.Product;
import vn.hcmute.projectmanagement.entity.SaleOff;

import java.util.List;

public interface SaleOffService {
    SaleOff getSaleOffById(long id);
    List<SaleOff> getAllSaleOff();
    List<Product> getAllProductBySaleOff(long id);
    SaleOff InsertSaleOff(SaleOff saleOff);
    void DeleteSaleOff(long id);
}
