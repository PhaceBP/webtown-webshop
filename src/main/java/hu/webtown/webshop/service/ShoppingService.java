package hu.webtown.webshop.service;

import hu.webtown.webshop.model.Product;

public interface ShoppingService {

	void addProductToShoppingBasket(Product product, int amount);
    
    Total calculateTotalPrice();
}
