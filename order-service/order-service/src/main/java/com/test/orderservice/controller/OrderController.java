package com.test.orderservice.controller;

import com.test.orderservice.dto.response.ProductResponse;
import com.test.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

    @PutMapping("/purchase/{id}")
    public ResponseEntity<ProductResponse> updateQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    ) {

        log.info("OrderController | updateQuantity | productId : " + productId);
        log.info("OrderController | updateQuantity | quantity : " + quantity);

        orderService.updateQuantity(productId,quantity);

        ProductResponse productResponse = orderService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }

}
