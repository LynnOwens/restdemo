package net.tofweb.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import net.tofweb.controller.CustomerController;
import net.tofweb.model.Customer;

/**
 * @author Lynn Owens
 *
 *         A CustomerResource is a web representation of a Customer object.
 *         Included in a CustomerResource is the link by which you may get all
 *         Customers and the link to this specified Customer.
 */
public class CustomerResource extends ResourceSupport {

	private final Customer customer;

	public CustomerResource(Customer customer) {
		this.customer = customer;
		this.add(new Link(customer.getId().toString(), "customer-uri"));
		this.add(linkTo(CustomerController.class).withRel("customers"));
		this.add(linkTo(methodOn(CustomerController.class).getById(customer.getId())).withSelfRel());
	}

	public Customer getCustomer() {
		return customer;
	}
}
