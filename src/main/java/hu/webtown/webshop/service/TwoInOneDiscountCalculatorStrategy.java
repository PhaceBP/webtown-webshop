package hu.webtown.webshop.service;

import java.util.Map;

import hu.webtown.webshop.model.Product;

public class TwoInOneDiscountCalculatorStrategy implements DiscountCalculatorStrategy {

	public double calculateTotalPriceOfPurchase(Map<Product, Integer> products) {

		double totalPrice = 0;

		for (Product p : products.keySet()) {

			totalPrice += calculatePartPrice(p, products.get(p));
		}

		return totalPrice;
	}

	private double calculatePartPrice(Product p, int numOfOrderedProduct) {

		int discountMultiplier = numOfOrderedProduct / 3;

		if (discountMultiplier > 0) {

			return (numOfOrderedProduct - discountMultiplier) * p.getPrice();
		} else {

			return numOfOrderedProduct * p.getPrice();
		}
	}
}
