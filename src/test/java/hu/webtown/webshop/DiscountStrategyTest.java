package hu.webtown.webshop;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hu.webtown.webshop.model.Cucumber;
import hu.webtown.webshop.model.Product;
import hu.webtown.webshop.model.Salami;
import hu.webtown.webshop.model.TireDuck;
import hu.webtown.webshop.service.DiscountCalculatorStrategy;
import hu.webtown.webshop.service.MegapackDiscountCalculatorStrategy;
import hu.webtown.webshop.service.ShoppingService;
import hu.webtown.webshop.service.TwoInOneDiscountCalculatorStrategy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-config.xml")
public class DiscountStrategyTest {

	
	private DiscountCalculatorStrategy twoInOneStrategy = new TwoInOneDiscountCalculatorStrategy();
	private DiscountCalculatorStrategy megapackStrategy = new MegapackDiscountCalculatorStrategy();
	
	@Autowired
	private ShoppingService shoppingService;
	
	@Test
	public void test2In1strategyCalculationWorksProperly(){
	
		Map<Product, Integer> orders = new HashMap<Product, Integer>();

		orders.put(new Salami(), 10); // Should be 14.000 based on the 2IN1
		orders.put(new Cucumber(), 11); // Should be 22.400 based on 2IN1, megapack is not enabled because the amount is only 11

		System.out.println(twoInOneStrategy.calculateTotalPriceOfPurchase(orders));
		System.out.println(megapackStrategy.calculateTotalPriceOfPurchase(orders));
		
		Assert.assertEquals(36400d, twoInOneStrategy.calculateTotalPriceOfPurchase(orders),0d);
		Assert.assertEquals(50800d, megapackStrategy.calculateTotalPriceOfPurchase(orders),0d);

		shoppingService.addProductToShoppingBasket(new Salami(), 10);
		shoppingService.addProductToShoppingBasket(new Cucumber(), 11);

		Assert.assertEquals(36400d,shoppingService.calculateTotalPrice(),0d);
	}
	
	@Test
	public void testMegapackStrategyCalculationWorksProperly(){
	
		Map<Product, Integer> orders = new HashMap<Product, Integer>();

		orders.put(new Salami(), 10); // Should be 14.000 based on the 2IN1, should be 20000 based on megpack not enabled
		orders.put(new TireDuck(), 2); // Should be 6000 no discount , no 2IN1 megapack not enabled
		orders.put(new Cucumber(), 25); // Should be 64000 based on megapack, should be 47600 based on 2IN1

		System.out.println(twoInOneStrategy.calculateTotalPriceOfPurchase(orders));
		System.out.println(megapackStrategy.calculateTotalPriceOfPurchase(orders));
		
		Assert.assertEquals(67600d, twoInOneStrategy.calculateTotalPriceOfPurchase(orders),0d);
		Assert.assertEquals(84000d, megapackStrategy.calculateTotalPriceOfPurchase(orders),0d);

		shoppingService.addProductToShoppingBasket(new Salami(), 10);
		shoppingService.addProductToShoppingBasket(new Cucumber(), 25);
		shoppingService.addProductToShoppingBasket(new TireDuck(), 2);

		Assert.assertEquals(67600d,shoppingService.calculateTotalPrice(),0d);
	}
}
