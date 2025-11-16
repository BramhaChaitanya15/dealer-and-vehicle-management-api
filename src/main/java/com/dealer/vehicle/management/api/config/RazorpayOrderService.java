package com.dealer.vehicle.management.api.config;

import com.dealer.vehicle.management.api.exceptions.PaymentFailedException;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayOrderService {

    private final RazorpayClient razorpayClient;

    public RazorpayOrderService(
        @Value("${razorpay.apiKey}") String apiKey,
        @Value("${razorpay.apiSecret}") String apiSecret
    ) throws Exception {
        razorpayClient = new RazorpayClient(apiKey, apiSecret);
    }

    public Order createOrder(double amountInRupees) throws Exception {
    	
    	try {
    	    // Razorpay order code
    		JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", (int) (amountInRupees * 100)); // Amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("payment_capture", 1);
            // other fields as needed can be added 
            //like receipt, date and time, note, id, status etc
            return razorpayClient.Orders.create(orderRequest);
    	} catch (Exception e) {
    	    throw new PaymentFailedException("Razorpay payment initiation failed.", e);
    	}
    }
}
