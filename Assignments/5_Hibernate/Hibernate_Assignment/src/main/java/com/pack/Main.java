package com.pack;

public class Main {

	public static void main(String[] args) {
		CustomerDAO dao = new CustomerDAO();
		// Create
		Customer customer = new Customer("Bobmm", "abcm@gmail.com", "8987894562");

		// Populate the customer object
		dao.saveOrUpdate(customer);
		System.out.println("Customer created: " + customer);

		// Read
		Customer retrievedCustomer = dao.getById(customer.getId());
		System.out.println("Retrieved customer: " + retrievedCustomer);

		// Update
		retrievedCustomer.setCsName("Jhone Shaw");
		dao.saveOrUpdate(retrievedCustomer);
		System.out.println("Updated customer: " + retrievedCustomer);

		// Delete
		dao.delete(retrievedCustomer.getId());
		System.out.println("Customer deleted");

		// Read again (should return null)
		Customer deletedCustomer = dao.getById(retrievedCustomer.getId());
		System.out.println("Deleted customer: " + deletedCustomer);
	}

}
