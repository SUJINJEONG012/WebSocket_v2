package com.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.entity.Contact;
import com.websocket.repository.ContactRepository;
import com.websocket.service.ContactService;

@RestController
public class ContactController {
	
	@Autowired
	ContactRepository repository;
	
	@Autowired
	ContactService service;
	
	// get contact
	@PostMapping("/contact")
	public Contact getOne(@RequestBody Contact param) {	//JSON으로 받은 param parsing 
		
		Contact data = repository.findTop1ByEmailOrderByWdateDesc(param.getEmail());
		
		if(data == null) {
			service.insert(param);
			return null;
		} else {
			return data;
		}
	}
	
	// insert DB
	@PostMapping("/contact/insert")
	public void save(@RequestBody Contact param) {
		service.insert(param);
	}
}