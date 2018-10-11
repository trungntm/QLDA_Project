package vn.hcmute.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnList;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnOne;
import vn.hcmute.projectmanagement.entity.Product;
import vn.hcmute.projectmanagement.entity.SaleOff;
import vn.hcmute.projectmanagement.service.SaleOffService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/saleoff")
public class SaleOffController {
    @Autowired
    SaleOffService saleOffService;

    @GetMapping("/")
    public DataReturnList<SaleOff> SaleOff(){
        List<SaleOff> saleOffs = saleOffService.getAllSaleOff();

        DataReturnList<SaleOff> dataReturnList = new DataReturnList<>();
        dataReturnList.setData(saleOffs);
        dataReturnList.setMessage("Get list sale off success");
        return dataReturnList;
    }

    @PostMapping("/create")
    public DataReturnOne<SaleOff> Create(@RequestBody SaleOff saleOff){
        SaleOff saleOff1 = saleOffService.InsertSaleOff(saleOff);

        DataReturnOne<SaleOff> dataReturnOne = new DataReturnOne<>();
        if(saleOff1 != null){
            dataReturnOne.setData(saleOff1);
            dataReturnOne.setMessage("Create sale off success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Create sale off fail");
        }
        return dataReturnOne;
    }

    @PutMapping("/update")
    public DataReturnOne<SaleOff> UpdateProduct(@RequestBody SaleOff saleOff){
        SaleOff saleOff1 = saleOffService.InsertSaleOff(saleOff);
        DataReturnOne<SaleOff> dataReturnOne = new DataReturnOne<>();

        if(saleOff1 != null){
            dataReturnOne.setData(saleOff1);
            dataReturnOne.setMessage("Update sale off success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Update sale off fail");
        }
        return dataReturnOne;
    }

    @DeleteMapping("/delete")
    public DataReturnOne<SaleOff> DeleteProduct(@RequestBody SaleOff saleOff){
        DataReturnOne<SaleOff> dataReturnOne = new DataReturnOne<>();
        try {
            saleOffService.DeleteSaleOff(saleOff.getId());
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Delete sale off success");
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Delete sale off fail" + e.getMessage());
        }
        return dataReturnOne;
    }
}
