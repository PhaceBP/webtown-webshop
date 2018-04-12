package hu.webtown.webshop.service;

import hu.webtown.webshop.model.Basket;
import hu.webtown.webshop.model.Product;

public abstract class AbstractDiscountCalculatorStrategy implements DiscountCalculatorStrategy {

	protected DiscountType discountType;

	protected AbstractDiscountCalculatorStrategy(DiscountType type) {
		this.discountType = type;
	}

	public Total calculateTotalPriceOfPurchase(Basket b) {

		double totalPrice = 0;

		for (Product p : b.getProductsFromBasket()) {

			Integer amount = b.getAmountFromTheSpecifiedProduct(p);
			totalPrice += calculatePartPrice(p, amount);
		}

		return new Total(discountType, totalPrice);
	}
	

}
