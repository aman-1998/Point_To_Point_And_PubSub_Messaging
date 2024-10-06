package personal.learning.jms.service;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import personal.learning.jms.model.OrderDetails;
import personal.learning.jms.model.Product;

public class OrderDetailsService {

	public ArrayList<OrderDetails> getOrderDetails(String username, String fromDate, String toDate) {
		
		ArrayList<OrderDetails> orderDetailsList = new ArrayList<>();
		
		if(StringUtils.equals(username, "start_neha")) {
			
			OrderDetails orderDetails = new OrderDetails();
			
			Product product1 = new Product();
			product1.setProductId("SH17263");
			product1.setProductCategory("Shirt & T-shirts");
			product1.setProductName("Roadster Blue Shirt cotton");
			product1.setDetails("L size | Chest 40 inches");
			product1.setBrand("Roadster");
			product1.setDiscountPercentage(22);
			product1.setProductAmount(999);
			
			Product product2 = new Product();
			product2.setProductId("JN92816");
			product2.setProductCategory("Pant & Trousers");
			product2.setProductName("Stretchable Blue Jeans slim");
			product2.setDetails("Size 32 | Slim");
			product2.setBrand("Flying Machine");
			product1.setDiscountPercentage(12);
			product2.setProductAmount(4499);
			
			Product product3 = new Product();
			product3.setProductId("ELH28272");
			product3.setProductCategory("Headsets & Earphones");
			product3.setProductName("Wireled SAMSUNG IC050 earphone");
			product3.setDetails("C-Type Wired Earphone");
			product2.setBrand("SAMSUNG");
			product3.setDiscountPercentage(34);
			product3.setProductAmount(1699);
			
			orderDetails.setOrderId("17266629925");
			orderDetails.setListOfProducts(Stream.of(product1, product2, product3).toList());
			orderDetails.setOrderDate("22-09-2024");
			orderDetails.setModeOfPayment("UPI");
			orderDetails.setOrderAmount(5453);
			orderDetails.setDeliveryCharge(40);
			
			orderDetailsList.add(orderDetails);
			
			//-----------------------------------------------------------------------------------------
			
			orderDetails = new OrderDetails();
			
			Product product4 = new Product();
			product4.setProductId("T2217263");
			product4.setProductCategory("Shirt & T-shirts");
			product4.setProductName("Polo T-shirt");
			product4.setDetails("L size | Chest 40 inches");
			product4.setBrand("US Polo Assn");
			product4.setDiscountPercentage(34);
			product4.setProductAmount(1399);
			
			orderDetails.setOrderId("272708273");
			orderDetails.setListOfProducts(Stream.of(product4).toList());
			orderDetails.setOrderDate("02-09-2024");
			orderDetails.setModeOfPayment("UPI");
			orderDetails.setOrderAmount(1399);
			orderDetails.setDeliveryCharge(40);
			
			orderDetailsList.add(orderDetails);
			
		} else if(StringUtils.equals(username, "aman_cool")) {
			
			OrderDetails orderDetails = new OrderDetails();
			
			Product product1 = new Product();
			product1.setProductId("L2625517");
			product1.setProductCategory("Electronics");
			product1.setProductName("Laptop");
			product1.setDetails("Dell Latitude 7020 | 13.3 inch");
			product1.setBrand("Dell");
			product1.setDiscountPercentage(13);
			product1.setProductAmount(46000);
			
			Product product2 = new Product();
			product2.setProductId("CH282997");
			product2.setProductCategory("Electronics");
			product2.setProductName("Samsung originals Adapter");
			product2.setDetails("C-Type | 25 Watt");
			product2.setBrand("SAMSUNG");
			product2.setDiscountPercentage(15);
			product2.setProductAmount(1399);
			
			orderDetails.setOrderId("73736213453");
			orderDetails.setListOfProducts(Stream.of(product1, product2).toList());
			orderDetails.setOrderDate("14-08-2024");
			orderDetails.setModeOfPayment("UPI");
			orderDetails.setOrderAmount(5672);
			orderDetails.setDeliveryCharge(0);
			
			orderDetailsList.add(orderDetails);
			
			//-----------------------------------------------------------------------------------------
			
			orderDetails = new OrderDetails();
			
			Product product3 = new Product();
			product3.setProductId("FT29399932");
			product3.setProductCategory("Sandals & Footwears");
			product3.setProductName("Campus Sports Shoe Lite20");
			product3.setDetails("Size 9 | 28 inches");
			product3.setBrand("Campus");
			product3.setDiscountPercentage(10);
			product3.setProductAmount(1799);
			
			orderDetails.setOrderId("7731801837");
			orderDetails.setListOfProducts(Stream.of(product3).toList());
			orderDetails.setOrderDate("30-06-2024");
			orderDetails.setModeOfPayment("UPI");
			orderDetails.setOrderAmount(1799);
			orderDetails.setDeliveryCharge(0);
			
			orderDetailsList.add(orderDetails);
			
			//-----------------------------------------------------------------------------------------
			
			orderDetails = new OrderDetails();
			
			Product product4 = new Product();
			product4.setProductId("FT29383773");
			product4.setProductCategory("Sandals & Footwears");
			product4.setProductName("Adidas Ultra Flex");
			product4.setDetails("Size 9 | 28 inches");
			product4.setBrand("Adidas");
			product4.setDiscountPercentage(12);
			product4.setProductAmount(3500);
			
			orderDetails.setOrderId("2827266442");
			orderDetails.setListOfProducts(Stream.of(product4).toList());
			orderDetails.setOrderDate("28-06-2024");
			orderDetails.setModeOfPayment("UPI");
			orderDetails.setOrderAmount(3500);
			orderDetails.setDeliveryCharge(0);
			
			orderDetailsList.add(orderDetails);
			
		} else if(StringUtils.equals(username, "madhu1998")) {
			
			OrderDetails orderDetails = new OrderDetails();
			
			Product product1 = new Product();
			product1.setProductId("SH17263");
			product1.setProductCategory("Shirt & T-shirts");
			product1.setProductName("Technosports Knitted Polo T-Shirt");
			product1.setDetails("L size | Chest 40 inches");
			product1.setBrand("Technosports");
			product1.setDiscountPercentage(15);
			product1.setProductAmount(540);
			
			orderDetails.setOrderId("273535529");
			orderDetails.setListOfProducts(Stream.of(product1).toList());
			orderDetails.setOrderDate("06-05-2024");
			orderDetails.setModeOfPayment("Net Banking");
			orderDetails.setOrderAmount(430);
			orderDetails.setDeliveryCharge(40);
			
			orderDetailsList.add(orderDetails);
		} else if(StringUtils.equals(username, "awsome_ravi")) {
			
			OrderDetails orderDetails = new OrderDetails();
			
			Product product1 = new Product();
			product1.setProductId("SAN29829");
			product1.setProductCategory("Sandals & Footwears");
			product1.setProductName("Aqualite Keto 09 Blue");
			product1.setDetails("Size 8 | 26.6 inches");
			product1.setBrand("Sparx");
			product1.setDiscountPercentage(10);
			product1.setProductAmount(599);
			
			orderDetails.setOrderId("377355223");
			orderDetails.setListOfProducts(Stream.of(product1).toList());
			orderDetails.setOrderDate("18-07-2024");
			orderDetails.setModeOfPayment("Cash On Delivery");
			orderDetails.setOrderAmount(599);
			orderDetails.setDeliveryCharge(40);
			
			orderDetailsList.add(orderDetails);
			
		} else if(StringUtils.equals(username, "mithila_nagri")) {
			
			OrderDetails orderDetails = new OrderDetails();
			
			Product product1 = new Product();
			product1.setProductId("WM276255373");
			product1.setProductCategory("Electronic appliances");
			product1.setProductName("Washing Machine Automatic M123");
			product1.setDetails("Size 7 litres");
			product1.setBrand("SAMSUNG");
			product1.setDiscountPercentage(20);
			product1.setProductAmount(28000);
			
			Product product2 = new Product();
			product1.setProductId("CH28663634");
			product1.setProductCategory("Electronics");
			product1.setProductName("Mobile Phone Charger");
			product1.setDetails("25 Watt charger");
			product1.setBrand("Motorola charger");
			product1.setDiscountPercentage(13);
			product1.setProductAmount(799);
			
			orderDetails.setOrderId("282663553");
			orderDetails.setListOfProducts(Stream.of(product1, product2).toList());
			orderDetails.setOrderDate("16-11-2024");
			orderDetails.setModeOfPayment("Cash On Delivery");
			orderDetails.setOrderAmount(850);
			orderDetails.setDeliveryCharge(40);
			
			orderDetailsList.add(orderDetails);
			
		} else if(StringUtils.equals(username, "imVKohli")) {
			
			OrderDetails orderDetails = new OrderDetails();
			
			Product product1 = new Product();
			product1.setProductId("BT017736634");
			product1.setProductCategory("Sports");
			product1.setProductName("Willow Greynicoles Bat");
			product1.setDetails("Cricket Bat 7");
			product1.setBrand("Greynicoles");
			product1.setDiscountPercentage(8);
			product1.setProductAmount(15999);
			
			orderDetails.setOrderId("9988524422");
			orderDetails.setListOfProducts(Stream.of(product1).toList());
			orderDetails.setOrderDate("11-10-2024");
			orderDetails.setModeOfPayment("UPI");
			orderDetails.setOrderAmount(15999);
			orderDetails.setDeliveryCharge(0);
			
			orderDetailsList.add(orderDetails);
		}
		
		return orderDetailsList;
	}
	
}
