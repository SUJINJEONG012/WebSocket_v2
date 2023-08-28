package com.websocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websocket.entity.Contact;

@Repository
public interface ContactRepository  extends JpaRepository<Contact, Long>{
	
	Contact findTop1ByEmailOrderByWdateDesc(String email);
}
