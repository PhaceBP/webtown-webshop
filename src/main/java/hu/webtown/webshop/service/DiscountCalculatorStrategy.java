package hu.webtown.webshop.service;

import hu.webtown.webshop.model.Basket;
import hu.webtown.webshop.model.Product;

public interface DiscountCalculatorStrategy {

	Total calculateTotalPriceOfPurchase(Basket b);
	
	double calculatePartPrice(Product p, int numOfOrderedProduct);
}
