package com.test.inventoryservice.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private String name;
    private long price;
    private long quantity;
}