package com.dealer.vehicle.management.api.service.serviceImpl;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.dealer.vehicle.management.api.entities.Dealer;
import com.dealer.vehicle.management.api.entities.Payment;
import com.dealer.vehicle.management.api.exceptions.DealerNotFoundException;
import com.dealer.vehicle.management.api.exceptions.PaymentFailedException;
import com.dealer.vehicle.management.api.helper.PaymentMethod;
import com.dealer.vehicle.management.api.helper.PaymentStatus;
import com.dealer.vehicle.management.api.payloads.PaymentRequestDto;
import com.dealer.vehicle.management.api.payloads.PaymentResponseDto;
import com.dealer.vehicle.management.api.repositories.DealerRepository;
import com.dealer.vehicle.management.api.repositories.PaymentRepository;
import com.dealer.vehicle.management.api.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private TaskScheduler taskScheduler;

    @Override
    public PaymentResponseDto initiatePayment(PaymentRequestDto requestDTO) {
        // Validate dealer exists
    	Long dId = requestDTO.getDealerId();
        Dealer dealer = dealerRepository.findById(dId)
                .orElseThrow(() -> new DealerNotFoundException("Dealer not found " + dId));

        Payment payment = new Payment();
        payment.setDealerId(requestDTO.getDealerId());
        payment.setAmount(requestDTO.getAmount());
        payment.setMethod(PaymentMethod.valueOf(requestDTO.getMethod().toUpperCase()));
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());
        if (payment.getMethod() == null || payment.getAmount() <= 0) {
            throw new PaymentFailedException("Payment details are invalid.");
        }
        Payment saved = paymentRepository.save(payment);

        // Schedule status update after 5 seconds
        taskScheduler.schedule(() -> {
            saved.setStatus(PaymentStatus.SUCCESS);
            paymentRepository.save(saved);
        }, Instant.now().plusSeconds(5));

        // return response to user
        PaymentResponseDto response = new PaymentResponseDto();
        response.setId(saved.getId());
        response.setDealerId(saved.getDealerId());
        response.setAmount(saved.getAmount());
        response.setMethod(saved.getMethod().name());
        response.setStatus(saved.getStatus().name());
        response.setCreatedAt(saved.getCreatedAt());
        return response;
    }
}

