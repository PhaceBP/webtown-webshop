package hu.webtown.webshop.service;

import java.util.Map;

import hu.webtown.webshop.model.Product;

public interface DiscountCalculatorStrategy {

	double calculateTotalPriceOfPurchase(Map<Product, Integer> products);
	
	double calculatePartPrice(Product p, int numOfOrderedProduct);
}
