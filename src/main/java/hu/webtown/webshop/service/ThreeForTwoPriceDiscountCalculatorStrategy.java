package hu.webtown.webshop.service;

import hu.webtown.webshop.model.Product;

public class ThreeForTwoPriceDiscountCalculatorStrategy extends AbstractDiscountCalculatorStrategy {

	public ThreeForTwoPriceDiscountCalculatorStrategy() {
		super(DiscountType.THREE_FOR_TWO_PRICE);
	}

	public double calculatePartPrice(Product p, int numOfOrderedProduct) {

		int discountMultiplier = numOfOrderedProduct / 3;

		if (discountMultiplier > 0) {

			return (numOfOrderedProduct - discountMultiplier) * p.getPrice();
		} else {

			return numOfOrderedProduct * p.getPrice();
		}
	}
}
