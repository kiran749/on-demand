package com.payment.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.payment.models.Payment;
import com.payment.models.TransactionRequest;
import com.payment.models.TransactionResponse;
import com.payment.repo.PaymentRepository;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	EmailService emailService;

	Random random = new Random();

    private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Override
	public void sendEmailDummy() throws AddressException {
		final Email email = DefaultEmail.builder()
				.from(new InternetAddress("gkiranreddy64@gmail.com"))
				.replyTo(new InternetAddress("gkiranreddy64@gmail.com"))
				.to(Lists.newArrayList(new InternetAddress("gkiranreddy64.et@ghrce.raisoni.net")))
				.subject("Dummy")
				.body("Hey your car has been washed.Thankyou for choosing us...")
				.encoding(String.valueOf(StandardCharsets.UTF_8)).build();
	
		emailService.send(email);
	}

	@Override
	public void sendEmail(Payment payment, String emailAddress) throws AddressException {
		
		final Email email = DefaultEmail.builder()
				.from(new InternetAddress("gkiranreddy64@gmail.com"))
				.replyTo(new InternetAddress("gkiranreddy64@gmail.com"))
				.to(Lists.newArrayList(new InternetAddress(emailAddress)))
				.subject("The Auto Fairy Receipt")
				.body(payment.toString())
				.encoding(String.valueOf(StandardCharsets.UTF_8)).build();
		emailService.send(email);
	}

	@Override
	public TransactionResponse doPaymentSetOrderPaymentStatus(TransactionRequest request)
			throws AddressException, JsonProcessingException {
		return null;
	}

	@Override
	public String paymentProcessing() {
		return new Random().nextBoolean() ? "success":"payment failed try again!";		
	}

	@Override
	public List<Payment> paymentList(String name) {
		return paymentRepository.findAll()
				.stream().filter(payment->payment.getCustomerName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Payment> paymentById(int id) throws JsonProcessingException {
		Optional<Payment> payment = paymentRepository.findById(id);
        log.info("Payment-Service List of Payments: {}", new ObjectMapper().writeValueAsString(payment));
        return payment;
	}

}
