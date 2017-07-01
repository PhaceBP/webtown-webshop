package hu.webtown.webshop.service;

import hu.webtown.webshop.model.Product;

public class MegapackDiscountCalculatorStrategy extends AbstractDiscountCalculatorStrategy {

	public double calculatePartPrice(Product p, int numOfOrderedProduct) {

		double originalPrice = numOfOrderedProduct * p.getPrice();

		if (numOfOrderedProduct >= 12 && p.isMegapackEnabled()) {

			int discountMultiplier = numOfOrderedProduct / 12;

			return originalPrice - (discountMultiplier * 6000);
		}

		return originalPrice;
	}
}
