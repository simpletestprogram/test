package com.test.inventoryservice.exception;


import lombok.Data;

@Data
public class InventoryServiceCustomException extends RuntimeException {

    private String errorCode;

    public InventoryServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}