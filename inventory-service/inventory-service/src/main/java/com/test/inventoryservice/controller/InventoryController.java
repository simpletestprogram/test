package com.test.inventoryservice.controller;

import com.test.inventoryservice.dto.request.ProductRequest;
import com.test.inventoryservice.dto.response.ProductResponse;
import com.test.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Log4j2
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {

        log.info("InventoryController | addProduct | productRequest : " + productRequest.toString());

        long productId = inventoryService.addProduct(productRequest);

        ProductResponse productResponse = inventoryService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId) {

        log.info("InventoryController | getProductById | productId : " + productId);

        ProductResponse productResponse = inventoryService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/restock/{id}")
    public ResponseEntity<ProductResponse> updateQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    ) {


        log.info("InventoryController | updateQuantity | productId : " + productId);
        log.info("InventoryController | updateQuantity | quantity : " + quantity);

        inventoryService.updateQuantity(productId,quantity);

        ProductResponse productResponse = inventoryService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }

}
