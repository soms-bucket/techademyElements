package com.pack.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.pack.model.Order;
import com.pack.model.OrderItem;

public class OrderDAO {
	// Create reference of Session Factory
	private SessionFactory factory;

	public OrderDAO() {
		Configuration cfg = new Configuration()
				.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
	}

	public void saveOrUpdate(Order order) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(order);
			System.out.println(order);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Order getById(int id) {
		Session session = factory.openSession();
		Order order = null;
		// Write the business logic to get an id
		try {
			order = session.get(Order.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return order;
	}

	public void delete(int id) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Order order = session.get(Order.class, id);
			if (order != null) {
				// Call delete ()
				session.delete(order);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void getAllOrder() {
		
		Session session = factory.openSession();
		
		try {
			
			Query<?> qry = session.createQuery("from Order"); // here Order is class
    	    @SuppressWarnings("unchecked")
			List<Order> list = (List<Order>) qry.list();
    	    for(Order pr: list) {
    	    	System.out.println(pr.getId()+" "+pr.getOrderDate()); 
    	    	printItem(pr.getOrderItems());
    	    }
    	    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void printItem(List<OrderItem> orderItems) {
		// TODO Auto-generated method stub
		System.out.println("\tID\tName\tQuantity");
		for(OrderItem p: orderItems) 
			System.out.println("\t"+p.getId()+"\t"+p.getProduct()+"\t"+p.getQuantity());
		System.out.println();
	}
			
}
