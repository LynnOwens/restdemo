package net.tofweb.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.tofweb.dao.CustomerDao;
import net.tofweb.model.Customer;

/**
 * @author Lynn Owens
 *
 *         Customer data access.
 */
@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@Resource
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.dao.CustomerDao#save(net.tofweb.model.Customer)
	 */
	public Customer save(Customer customer) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(customer);

		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.dao.CustomerDao#findById(java.lang.Integer)
	 */
	public Customer findById(Integer customerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return (Customer) currentSession.get(Customer.class, customerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.dao.CustomerDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public Collection<Customer> findAll() {
		Session currentSession = sessionFactory.getCurrentSession();
		return (List<Customer>) currentSession.createQuery("from Customer").getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.dao.CustomerDao#delete(net.tofweb.model.Customer)
	 */
	public void delete(Customer customer) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(customer);
	}
}
