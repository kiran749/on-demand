package com.payment.controller;

import java.util.List;
import java.util.Optional;

import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payment.models.Payment;
import com.payment.models.TransactionRequest;
import com.payment.models.TransactionResponse;
import com.payment.repo.PaymentRepository;
import com.payment.service.PaymentServiceImpl;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	
	@PostMapping("/pay")
	public TransactionResponse payAmount(@RequestBody TransactionRequest transactionRequest) throws AddressException, JsonProcessingException{
		return paymentServiceImpl.doPaymentSetOrderPaymentStatus(transactionRequest);
	}
	
	@GetMapping("/get-payments-list")
    public ResponseEntity<List<Payment>> paymentList() {
        return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get-payment/{name}")
    public List<Payment> paymentListOfName(@PathVariable String name) {
        return paymentServiceImpl.paymentList(name);
    }

    @GetMapping("/send-mail")
    public String sendMail() throws AddressException {
        Payment payment = new Payment();
        payment.setPaymentId(1232);
        payment.setPaymentStatus("Success");
        paymentServiceImpl.sendEmail(payment, "rishikeshghadole11@gmail.com");
        return "Sent Mail";
    }

    @GetMapping("/send-dummy")
    public String sendDummy() throws AddressException {
        paymentServiceImpl.sendEmailDummy();
        return "SEnt MAIL";
    }

    @RequestMapping("/test-payment")
    public String testPayment() {
        return "Payment gateway up and running";
    }

    @GetMapping("/get-payment-byId/{Id}")
    public Optional<Payment> getPaymentById(@PathVariable int id) throws JsonProcessingException {
        return paymentServiceImpl.paymentById(id);
    }
}

