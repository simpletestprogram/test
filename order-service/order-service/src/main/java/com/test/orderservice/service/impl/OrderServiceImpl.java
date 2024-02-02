package com.test.orderservice.service.impl;

import com.test.orderservice.dto.response.ProductResponse;
import com.test.orderservice.entity.Product;
import com.test.orderservice.exception.OrderServiceCustomException;
import com.test.orderservice.repository.ProductRepository;
import com.test.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse getProductById(long productId) {

        Product product
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new OrderServiceCustomException("Product with given Id not found","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse
                = new ProductResponse();

        copyProperties(product, productResponse);

        log.info("InventoryServiceImpl | getProductById | productResponse :" + productResponse.toString());

        return productResponse;
    }

    @Override
    public void updateQuantity(long productId, long quantity) {

        Product product
                = productRepository.findById(productId)
                .orElseThrow(() -> new OrderServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));

        if(product.getQuantity() < quantity) {
            throw new OrderServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("OrderServiceImpl | updateQuantity | Product Id : " + product.getProductId() + "Quantity updated Successfully");

    }

}