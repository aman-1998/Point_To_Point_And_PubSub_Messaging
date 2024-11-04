package personal.learning.jms.model;

import java.io.Serializable;
import java.util.List;

public class OrderDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String orderId;
	
	private List<Product> listOfProducts;
	
	private String orderDate;
	
	private String modeOfPayment;
	
	private int deliveryCharge;
	
	private int orderAmount;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public int getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(int deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", listOfProducts=" + listOfProducts + ", orderDate=" + orderDate
				+ ", modeOfPayment=" + modeOfPayment + ", deliveryCharge=" + deliveryCharge + ", orderAmount="
				+ orderAmount + "]";
	}
	
}
