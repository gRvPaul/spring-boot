package com.cma.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import java.util.List;
import java.util.Date;
import javax.validation.Valid;

import com.cma.bean.CustomerBean;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author UNGERW
 */

@Component
@Path("/customer")
//@RestController
//@RequestMapping(value = "/customer")
public class HelloWorldService {

	private CustomerBean customerBean;

    	@Inject
	public  HelloWorldService(final CustomerBean customerBean) {
		this.customerBean = customerBean;
	}


	@GET
	@Path("/save")
	public Response saveCustomer() {
		this.customerBean.saveCustomer(null, null);
	
		return Response.status(200).entity("Customer saved !!").build();
	}
/*
	//@GET
	//@Path("/name")
	public Response getUserNameByEmail(@RequestParam(value = "email", required = true) String email) {
		email = "paul.gourav@tcs.com";
		List<Customer> customers = this.customerRepository.findByEmail(email);
		if (customers.size() > 0) {
			return Response.status(200).entity("For email : " + email + " User name : " + customers.get(0).getName()).build();
		} else {
			return Response.status(200).entity("For email " + email + "No user found !!").build();
		}
	}

*/
	@GET
	@Path("/checkapi")
	//@RequestMapping(value = "/checkapi", method = RequestMethod.GET)
	public Response test() {
		return Response.status(200).entity("Western Union Page , - welcome Date: 09.05.2019").build();
	}
}

