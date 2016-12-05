package net.tofweb.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tofweb.dao.CustomerDao;
import net.tofweb.model.Customer;
import net.tofweb.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerDao customerDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.service.CustomerService#save(net.tofweb.model.Customer)
	 */
	public Customer save(Customer customer) {
		return customerDao.save(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.service.CustomerService#findById(java.lang.Integer)
	 */
	public Customer findById(Integer customerId) {
		return customerDao.findById(customerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.service.CustomerService#findAll()
	 */
	public Collection<Customer> findAll() {
		return customerDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.service.CustomerService#delete(java.lang.Integer)
	 */
	public void delete(Integer customerId) {
		Customer customer = new Customer();
		customer.setId(customerId);
		customerDao.delete(customer);
	}
}
