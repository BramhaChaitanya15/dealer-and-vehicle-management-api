package com.dealer.vehicle.management.api.service;

import com.dealer.vehicle.management.api.payloads.PaymentRequestDto;
import com.dealer.vehicle.management.api.payloads.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto initiatePayment(PaymentRequestDto requestDTO);
}
