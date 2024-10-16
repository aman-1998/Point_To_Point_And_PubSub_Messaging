package personal.learning.jms.model;

import java.io.Serializable;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String productId;
	
	private String productCategory;
	
	private String brand;
	
	private String productName;
	
	private String details;
	
	private int productAmount;
	
	private int discountPercentage;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	
	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productCategory=" + productCategory + ", productName="
				+ productName + ", productAmount=" + productAmount + ", discountPercentage=" + discountPercentage + "]";
	}
	
}
