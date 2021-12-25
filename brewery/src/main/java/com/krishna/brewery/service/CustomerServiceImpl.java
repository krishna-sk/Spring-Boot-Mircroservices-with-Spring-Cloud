package com.krishna.brewery.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.krishna.brewery.web.model.CustomerDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		return CustomerDto.builder().id(UUID.randomUUID()).name("Joe Buck").build();
	}

	@Override
	public CustomerDto saveNewCustomer(@RequestBody CustomerDto customerDto) {
		return CustomerDto.builder().id(UUID.randomUUID()).build();
	}

	@Override
	public void updateCustomer(UUID customerId, @RequestBody CustomerDto customerDto) {
		// todo impl
		log.debug("Updating....");
	}

	@Override
	public void deleteById(UUID customerId) {
		log.debug("Deleting.... ");
	}
}