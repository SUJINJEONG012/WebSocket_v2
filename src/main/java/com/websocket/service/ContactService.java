package com.websocket.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.entity.Contact;
import com.websocket.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository repository;
	
	public void insert(Contact param) {
		param.setWdate(LocalDateTime.now());
		repository.save(param); // db저장
	}
}
