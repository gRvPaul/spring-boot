package com.cma.bean;

import com.cma.model.Customer;

public interface CustomerBean {
	void saveCustomer(final String name, final String email);
	Customer getCustomerByEmail(final String email);
}
