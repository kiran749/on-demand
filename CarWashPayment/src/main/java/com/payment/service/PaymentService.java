package com.payment.service;

import java.util.List;
import java.util.Optional;

import javax.mail.internet.AddressException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payment.models.Payment;
import com.payment.models.TransactionRequest;
import com.payment.models.TransactionResponse;

public interface PaymentService {

	public void sendEmailDummy() throws AddressException;

    public void sendEmail(Payment payment, String emailAddress) throws AddressException;

    public TransactionResponse doPaymentSetOrderPaymentStatus(TransactionRequest request) throws AddressException, JsonProcessingException;

    public String paymentProcessing();

    public List<Payment> paymentList(String name);

    public Optional<Payment> paymentById(int id) throws JsonProcessingException;
	
}
