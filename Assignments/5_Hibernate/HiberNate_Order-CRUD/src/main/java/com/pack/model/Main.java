package com.pack.model;

import com.pack.dao.OrderDAO;
import com.pack.dao.OrderItemDAO;
import com.pack.model.Order;
import com.pack.model.OrderItem;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

	static OrderDAO orderDAO = new OrderDAO();
	static OrderItemDAO orderItemDAO = new OrderItemDAO();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		 int choice;
		 Order order = null;
		 do {
			 System.out.println("\n1. Do Order");
			 System.out.println("2. Add Order Item");
			 System.out.println("3. Add Order Item By Order Id");
			 System.out.println("4. Display Orders");
			 System.out.println("5. Display Order Items By Order ID");
			 System.out.println("6. Update Order Item by ID");
			 System.out.println("7. Delete Order Item by ID");
			 System.out.println("8. Delete Order by ID");
			 System.out.println("9. Exit");
			 System.out.print("Enter your choice: ");
			 choice = scanner.nextInt();
			 scanner.nextLine();
			 
			 
			 switch (choice) {
				 case 1:
					 order = addOrder();
					 break;
				 case 2:
					 //displayAllEmployeeRecords();
					 if(order != null) addOrderItem(order);
					 else System.out.println("You Have to create Order first!!!!\nYou may use Option 3");
					 break;
				 case 3:
					 //displayAllEmployeeRecords();
					 addOrderItemById();
					 break;
				 case 4:
					 displayAllOrder();
					 break;	
				 case 5:
					 displayAllOrderItems();
					 break;
				 case 6:
					 updateOrderItem();
					 break;
				 case 7:
					 deleteOrderItem();
					 break;
				 case 8:
					 deleteOrder();
					 order = null;					 
					 break;
				 case 9:
					 System.out.println("Exiting...");
					 break;
				 default:
					 System.out.println("Invalid choice. Please enter a number between 1 and 9.");
			}
		 } while (choice != 9);
		
		

	}

	private static void addOrderItemById() {
		System.out.print("Enter Order ID: "); int orderId = scanner.nextInt();
		Order order = orderDAO.getById(orderId);
		if(order!=null)addOrderItem(order);
		else System.out.println("Order Id not found!!!");
		
		
	}

	private static void displayAllOrder() {
		// TODO Auto-generated method stub
		orderDAO.getAllOrder();
	}

	private static void deleteOrder() {
		// TODO Auto-generated method stub
		System.out.print("Enter Order ID: "); int orderId = scanner.nextInt();
		// Delete Order
		orderDAO.delete(orderId);	
		
	}

	private static void deleteOrderItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter Item ID: "); int itemId = scanner.nextInt();
		// Delete OrderItem
		orderItemDAO.delete(itemId);
		
	}

	private static void updateOrderItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter Item ID: "); int itemId = scanner.nextInt();
		System.out.print("Enter Item Quantity: "); int iQuantity = scanner.nextInt();
		OrderItem item = orderItemDAO.getItemById(itemId);
		// Update OrderItem
		item.setQuantity(iQuantity);
		orderItemDAO.saveOrUpdate(item);
		System.out.println("Updated order item: " + item.getId());
		
	}

	private static void displayAllOrderItems() {
		System.out.print("Enter Order ID: "); int orderId = scanner.nextInt();
		// Read OrderItems
		List<OrderItem> orderItems = orderItemDAO.getById(orderId);
		System.out.println("Order items for order " + orderId + ": ");
		Stream<OrderItem> stream = orderItems.stream();
		System.out.println("ID\tName\tQuantity");
		stream.forEach(p -> System.out.println(p.getId()+"\t"+p.getProduct()+"\t"+p.getQuantity()));
		System.out.println();
		
	}

	private static void addOrderItem(Order order) {
		// TODO Auto-generated method stub
		
		// Create OrderItem
		OrderItem orderItem1 = new OrderItem();
		// Call setter for orderIem1
		orderItem1.setOrder(order);
		orderItem1.setProduct("Pizza");
		orderItem1.setQuantity(5);
		// Save OrderItem
		orderItemDAO.saveOrUpdate(orderItem1);
		System.out.println("Order item created: " + orderItem1.getId());
		
	}

	private static Order addOrder() {
		// Create Order
		Order order = new Order();
		// Call setter for date
		Date orderDate = new Date();
		order.setOrderDate(orderDate);
		// save order
		orderDAO.saveOrUpdate(order);
		System.out.println("Order created: " + order.getId());
		return order;
	}

}
