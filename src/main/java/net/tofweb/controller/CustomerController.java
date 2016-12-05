package net.tofweb.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.tofweb.exception.InvalidCustomerException;
import net.tofweb.exception.ResourceNotFoundException;
import net.tofweb.model.Customer;
import net.tofweb.resource.CustomerResource;
import net.tofweb.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Resource
	private CustomerService customerService;

	/**
	 * Get all the Customers
	 * 
	 * @return Collection<Customer>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Resources<CustomerResource> get() {
		Collection<Customer> customers = customerService.findAll();

		// Stream the collection of customers through two maps
		// The first map casts the object to a Customer - needed because steams
		// work on Objects
		// The second map builds a CustomerResource with the specified Customer
		// Finally the steam is turned back into a list for output
		List<CustomerResource> customerResourceList = customers.stream().map(Customer.class::cast)
				.map(CustomerResource::new).collect(Collectors.toList());

		return new Resources<CustomerResource>(customerResourceList);
	}

	/**
	 * Get a Customer by its id.
	 * 
	 * If the Customer is not found, throw a 404
	 * 
	 * @param customerId
	 * @return Customer
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{customerId}")
	public CustomerResource getById(@PathVariable Integer customerId) {
		Customer customer = customerService.findById(customerId);

		if (customer == null) {
			throw new ResourceNotFoundException();
		}

		return new CustomerResource(customer);
	}

	/**
	 * Add the specified Customer
	 * 
	 * @param customer
	 * @return The location of the created Customer
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Customer customer) {

		validateCustomer(customer);

		Customer result = customerService.save(customer);
		Link customerLink = new CustomerResource(result).getLink("self");

		return ResponseEntity.created(URI.create(customerLink.getHref())).build();
	}

	/**
	 * Update an existing Customer with the specified values
	 * 
	 * @param customer
	 * @return Customer
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Customer update(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	/**
	 * Delete the Customer specified by the id.
	 * 
	 * If that Customer does not exist to delete, then throw a 404
	 * 
	 * @param customerId
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{customerId}")
	public void delete(@PathVariable Integer customerId) {
		try {
			customerService.delete(customerId);
		} catch (HibernateOptimisticLockingFailureException ex) {
			// Honestly, logging this is optional
			logger.info("Unable to delete Customer", ex);
			throw new ResourceNotFoundException(ex);
		}
	}

	/*
	 * Add any validations desired here
	 */
	private void validateCustomer(Customer customer) {
		if (customer.getName() == null) {
			throw new InvalidCustomerException("Customer name is required");
		}
	}
}
