package com.ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ra.dto.ProductAvailability;
import com.ra.dto.ProductRequest;
import com.ra.dto.ProductResponse;
import com.ra.dto.ProductResponse;
import com.ra.service.RestApplicationService;

import java.util.List;

@RestController
public class RestApplicationController {

    @Autowired
    RestApplicationService service;
    
    //Question 1
    @GetMapping("/getInvPicture")
    public ProductResponse getProductPicture(@RequestBody ProductRequest request) {

        return service.getProductDetails(request);
    }
    
    
    //Question 2
    @GetMapping("/getProdAvailability")
    public ProductAvailability getProdAvailability(
            @RequestParam("storeNo") String storeNo,
            @RequestParam("productId") String productId,
            @RequestParam("reqQty") Double reqQty,
            @RequestParam("reqDate") String reqDate) {
    	return service.getProdAvailability(storeNo, productId, reqQty, reqDate);
    }
}
