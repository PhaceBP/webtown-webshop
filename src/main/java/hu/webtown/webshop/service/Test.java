package hu.webtown.webshop.service;

import java.util.HashMap;
import java.util.Map;

import hu.webtown.webshop.model.Cucumber;
import hu.webtown.webshop.model.Product;
import hu.webtown.webshop.model.Salami;

public class Test {

	public static void main(String[] args) {

		TwoInOneDiscountCalculatorStrategy s = new TwoInOneDiscountCalculatorStrategy();
		MegapackDiscountCalculatorStrategy s2 = new MegapackDiscountCalculatorStrategy();

		Map<Product, Integer> orders = new HashMap<Product, Integer>();

		orders.put(new Salami(), 10);
		orders.put(new Cucumber(), 11);

		System.out.println(s.calculateTotalPriceOfPurchase(orders));
		System.out.println(s2.calculateTotalPriceOfPurchase(orders));

		DefaultShoppingService ss = new DefaultShoppingService();

		ss.init();
		ss.addProductToShoppingBasket(new Salami(), 10);
		ss.addProductToShoppingBasket(new Cucumber(), 11);

		System.out.println("Optimum price is : " + ss.calculateTotalPrice());
	}
}
