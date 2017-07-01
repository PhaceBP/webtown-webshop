package hu.webtown.webshop.model;

public abstract class MegapackAwareProduct extends Product {

	public MegapackAwareProduct(String name, double price) {
		super(name, price, true);
	}
}
