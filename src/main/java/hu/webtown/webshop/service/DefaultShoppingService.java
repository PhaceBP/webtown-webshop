package hu.webtown.webshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import hu.webtown.webshop.model.Product;

@Service
@Scope(value = "prototype")
public class DefaultShoppingService implements ShoppingService {

	private final Map<Product, Integer> BASKET = new HashMap<Product, Integer>();

	private final List<DiscountCalculatorStrategy> DISCOUNT_CALCULATORS = new ArrayList<DiscountCalculatorStrategy>();

	@PostConstruct
	public void init() {
		DISCOUNT_CALCULATORS.add(new TwoInOneDiscountCalculatorStrategy());
		DISCOUNT_CALCULATORS.add(new MegapackDiscountCalculatorStrategy());
	}

	public double calculateTotalPrice() {

		double optimumPrice = DISCOUNT_CALCULATORS.get(0).calculateTotalPriceOfPurchase(BASKET);

		for (int i = 1; i < DISCOUNT_CALCULATORS.size(); i++) {

			double tempPrice = DISCOUNT_CALCULATORS.get(i).calculateTotalPriceOfPurchase(BASKET);

			if (tempPrice < optimumPrice) {
				optimumPrice = tempPrice;
			}
		}

		return optimumPrice;

	}

	public void addProductToShoppingBasket(Product product, int amount) {

		Integer numOfAlreadyOrderedProduct = BASKET.get(product);

		if (numOfAlreadyOrderedProduct == null) {
			BASKET.put(product, amount);
		} else {
			BASKET.put(product, ++numOfAlreadyOrderedProduct);
		}

	}
}
