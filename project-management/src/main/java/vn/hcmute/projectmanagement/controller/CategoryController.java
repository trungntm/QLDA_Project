package vn.hcmute.projectmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/category")
public class CategoryController {

    @GetMapping("/")
    public String Category(){
        return "success";
    }
}
