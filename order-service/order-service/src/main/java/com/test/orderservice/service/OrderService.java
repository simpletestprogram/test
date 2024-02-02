package com.test.orderservice.service;

import com.test.orderservice.dto.response.ProductResponse;

public interface OrderService {

    //Check Product Availability: Check if a product is available in the inventory based on its ID, returning its current quantity.
    ProductResponse getProductById(long productId);

    //Update Product Quantity: Adjust the quantity of a specific product in the inventory, which could be triggered by product purchases or restocking.
    void updateQuantity(long productId, long quantity);
}