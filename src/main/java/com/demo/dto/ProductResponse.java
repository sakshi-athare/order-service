package com.demo.dto;

import lombok.Data;

@Data
public class ProductResponse {
	 private int productId;
	    private String name;
	    private double price;
	    private int quantity;   
	    private double discount;
}
