package hu.webtown.webshop.service;

import java.util.Map;

import hu.webtown.webshop.model.Product;

public class MegapackDiscountCalculatorStrategy implements DiscountCalculatorStrategy {

	public double calculateTotalPriceOfPurchase(Map<Product, Integer> products) {

		double totalPrice = 0;

		for (Product p : products.keySet()) {

			totalPrice += calculatePartPrice(p, products.get(p));
		}

		return totalPrice;
	}

	private double calculatePartPrice(Product p, int numOfOrderedProduct) {

		return p.isMegapackEnabled() && numOfOrderedProduct >= 12 ? numOfOrderedProduct * p.getPrice() - 6000
				: numOfOrderedProduct * p.getPrice();
	}
}
