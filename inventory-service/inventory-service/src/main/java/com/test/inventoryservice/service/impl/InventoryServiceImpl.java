package com.test.inventoryservice.service.impl;

import com.test.inventoryservice.dto.request.ProductRequest;
import com.test.inventoryservice.dto.response.ProductResponse;
import com.test.inventoryservice.entity.Product;
import com.test.inventoryservice.exception.InventoryServiceCustomException;
import com.test.inventoryservice.repository.ProductRepository;
import com.test.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
@Log4j2
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {

        Product product
                = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        product = productRepository.save(product);

        log.info("InventoryServiceImpl | addProduct | Product Id : " + product.getProductId());
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {

        Product product
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new InventoryServiceCustomException("Product with given Id not found","PRODUCT_NOT_FOUND"));

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
                .orElseThrow(() -> new InventoryServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);
        log.info("InventoryServiceImpl | updateQuantity | Product Id : " + product.getProductId() + "Quantity updated Successfully");

    }

}