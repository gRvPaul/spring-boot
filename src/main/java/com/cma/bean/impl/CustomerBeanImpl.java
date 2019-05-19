package com.cma.bean.impl;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cma.model.Customer;
import com.cma.bean.CustomerBean;
import com.cma.dao.CustomerRepository;

@Service("customerBean")
public class CustomerBeanImpl implements CustomerBean {
	@Autowired(required = true)
	private CustomerRepository customerRepository;

	public CustomerBeanImpl() {
	}

	@Override
	public void saveCustomer(final String name, final String email) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setDate(new Date());

		this.customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerByEmail(final String email) {
		/*List<Customer> customers = this.customerRepository.findByEmail(email);
		
		if (customers.size() == 0) {
			return null;
		} else {
			return customers.get(0);
		}*/
		Customer customer = this.customerRepository.findByEmail(email);
		return customer;
	}
}

