package com.dealer.vehicle.management.api.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.dealer.vehicle.management.api.payloads.PaymentRequestDto;
import com.dealer.vehicle.management.api.payloads.PaymentResponseDto;
import com.dealer.vehicle.management.api.service.PaymentService;
import com.razorpay.Order;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.dealer.vehicle.management.api.config.RazorpayOrderService;

@RestController
@RequestMapping("/api/payment")
@SecurityRequirement(name = "bearerAuth") // JWT for Swagger UI
public class PaymentController {

	@Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RazorpayOrderService razorpayOrderService;

    //Simulates local payment and schedules status change.
    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponseDto> initiatePayment(
            @RequestBody PaymentRequestDto requestDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        PaymentResponseDto result = paymentService.initiatePayment(requestDTO);
        return ResponseEntity.ok(result);
    }

    //Creates a Razorpay order (for frontend use)
    @PostMapping("/razorpay/order")
    public ResponseEntity<String> createRazorpayOrder(
            @RequestParam double amount
    ) {
        try {
            Order order = razorpayOrderService.createOrder(amount);
            // Return Razorpay order info to frontend
            return ResponseEntity.ok(order.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed: " + e.getMessage());
        }
    }
}

