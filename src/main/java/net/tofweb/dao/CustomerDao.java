package net.tofweb.dao;

import java.util.Collection;

import net.tofweb.model.Customer;

/**
 * @author Lynn Owens
 *
 *         Customer data access
 */
public interface CustomerDao {
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
	 * Delete the Customer specified
	 * 
	 * @param customer
	 */
	void delete(Customer customer);

}
