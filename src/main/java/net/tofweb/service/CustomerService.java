package net.tofweb.service;

import java.util.Collection;

import net.tofweb.model.Customer;

public interface CustomerService {

	/**
	 * Persist the provided Customer. Doing so appends an id to the Customer.
	 * 
	 * @param customer
	 * @return the persisted Customer
	 */
	Customer save(Customer customer);

	/**
	 * Find a Customer by the specified id
	 * 
	 * @param customerId
	 * @return Customer
	 */
	Customer findById(Integer customerId);

	/**
	 * Get all the Customers in the database
	 * 
	 * @return Collection<Customer>
	 */
	Collection<Customer> findAll();

	/**
	 * Delete the Customer indicated by the specified id
	 * 
	 * @param customerId
	 */
	void delete(Integer customerId);
}
