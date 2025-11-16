package com.dealer.vehicle.management.api.payloads;

public class PaymentRequestDto {
    private Long dealerId;
    private Double amount;
    private String method; // "UPI", "CARD", "NETBANKING"

    // getters and setters
	public Long getDealerId() {
		return dealerId;
	}
	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
    
}
