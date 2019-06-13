package com.cma.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import java.util.List;
import java.util.Date;
import javax.validation.Valid;

//import com.cma.bean.CustomerBean;
//import com.cma.model.Customer;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gourav
 */

@RestController
@RequestMapping(value = "/customer")
public class HelloWorldService {

//	private CustomerBean customerBean;

    	@Inject
	public  HelloWorldService() {
//		this.customerBean = customerBean;
	}
/*
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public Response saveCustomer(@Valid @RequestParam(value = "name", required = true) final String name, 
		@Valid @RequestParam(value = "email", required = true) final String email) {
		this.customerBean.saveCustomer(name, email);
	
		return Response.status(200).entity("Customer saved !!").build();
	}

	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public Response getUserNameByEmail(@RequestParam(value = "email", required = true) final String email) {
		Customer customer = this.customerBean.getCustomerByEmail(email);
		
		if (customer != null) {
			return Response.status(200).entity("Customer name : " + customer.getName()).build();
		} else {
			return Response.status(200).entity("No user found !!").build();
		}
	}
*/
	@RequestMapping(value = "/checkapi", method = RequestMethod.GET)
	public Response test() {
		return Response.status(200).entity("Today's Demo Page, - welcome Date: " + new Date()).build();
	}
}

