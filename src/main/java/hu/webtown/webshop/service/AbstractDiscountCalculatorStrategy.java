package hu.webtown.webshop.service;

import java.util.Map;

import hu.webtown.webshop.model.Product;

public abstract class AbstractDiscountCalculatorStrategy implements DiscountCalculatorStrategy {

	public double calculateTotalPriceOfPurchase(Map<Product, Integer> products) {

		double totalPrice = 0;

		for (Product p : products.keySet()) {

			totalPrice += calculatePartPrice(p, products.get(p));
		}

		return totalPrice;
	}
}
