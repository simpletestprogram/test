package com.test.inventoryservice.service;

import com.test.inventoryservice.dto.request.ProductRequest;
import com.test.inventoryservice.dto.response.ProductResponse;

public interface InventoryService {


    //Add New Product to Inventory: Accept product details including name, price, and quantity, and store them in the inventory.
    long addProduct(ProductRequest productRequest);

    //Check Product Availability: Check if a product is available in the inventory based on its ID, returning its current quantity.
    ProductResponse getProductById(long productId);

    //Update Product Quantity: Adjust the quantity of a specific product in the inventory, which could be triggered by product purchases or restocking.
    void updateQuantity(long productId, long quantity);
}