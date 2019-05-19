package com.cma.dao;

import com.cma.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	//List<Customer> findByEmail(final String email);
	Customer findByEmail(final String email);
}
