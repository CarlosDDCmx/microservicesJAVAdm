package com.cddc.paymentservice.service;

import com.cddc.paymentservice.model.Payment;
import com.cddc.paymentservice.repository.PaymentRepository;
import com.cddc.paymentservice.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(PaymentRequest request) {
        // Mock payment processing with simulated values
        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setStatus("PAYMENT_SUCCESS");
        payment.setTransactionId(generateTransactionId());

        return paymentRepository.save(payment);
    }

    private String generateTransactionId() {
        return "TX-" + UUID.randomUUID().toString();
    }
}