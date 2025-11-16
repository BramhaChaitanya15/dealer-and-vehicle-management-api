package com.dealer.vehicle.management.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dealer.vehicle.management.api.payloads.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ApiResponse> handlePaymentFailedException(PaymentFailedException ex) {
    	String message = ex.getMessage();
    	ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ApiResponse> handleVehicleNotFoundException(VehicleNotFoundException ex) {
    	String message = ex.getMessage();
    	ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DealerNotFoundException.class)
    public ResponseEntity<ApiResponse> handleDealerNotFoundException(DealerNotFoundException ex) {
    	String message = ex.getMessage();
    	ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
