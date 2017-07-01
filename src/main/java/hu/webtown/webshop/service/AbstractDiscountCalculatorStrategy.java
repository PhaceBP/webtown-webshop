package hu.webtown.webshop.service;

import java.util.Map;

import hu.webtown.webshop.model.Product;

public abstract class AbstractDiscountCalculatorStrategy implements DiscountCalculatorStrategy {

	protected DiscountType discountType;

	protected AbstractDiscountCalculatorStrategy(DiscountType type) {
		this.discountType = type;
	}

	public Total calculateTotalPriceOfPurchase(Map<Product, Integer> products) {

		double totalPrice = 0;

		for (Product p : products.keySet()) {

			totalPrice += calculatePartPrice(p, products.get(p));
		}

		return new Total(discountType, totalPrice);
	}
}
