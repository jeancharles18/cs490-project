package mum.pmp.mstore.domain;

public class OrderLine {
	
	private int quantity;
	private Product product;

	public OrderLine() {	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
