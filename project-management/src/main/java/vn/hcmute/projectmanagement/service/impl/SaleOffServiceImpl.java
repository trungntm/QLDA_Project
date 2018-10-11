package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Product;
import vn.hcmute.projectmanagement.entity.SaleOff;
import vn.hcmute.projectmanagement.repository.SaleOffRepository;
import vn.hcmute.projectmanagement.service.SaleOffService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SaleOffServiceImpl implements SaleOffService {

    @Autowired
    SaleOffRepository saleOffRepository;

    @Override
    public SaleOff getSaleOffById(long id) {
        return saleOffRepository.findById(id).get();
    }

    @Override
    public List<SaleOff> getAllSaleOff() {
        return saleOffRepository.findAll();
    }

    @Override
    public List<Product> getAllProductBySaleOff(long id) {
        Set<Product> products = saleOffRepository.findById(id).get().getProducts();
        List<Product> lstProducts = new ArrayList<>(products);
        return lstProducts;
    }

    @Override
    public SaleOff InsertSaleOff(SaleOff saleOff) {
        return saleOffRepository.save(saleOff);
    }

    @Override
    public void DeleteSaleOff(long id) {
        Optional<SaleOff> saleOff = saleOffRepository.findById(id);
        if(saleOff.isPresent()){
            SaleOff saleOff1 = saleOff.get();
            saleOff1.setStatus("false");
        }
    }
}
