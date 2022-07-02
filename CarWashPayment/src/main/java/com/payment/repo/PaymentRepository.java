package com.payment.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.payment.models.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, Integer>{

	
	List<Payment> findAll();
	
}
