package com.dealer.vehicle.management.api.payloads;

import jakarta.validation.constraints.NotEmpty;

public class VehicleDto {

	private Long id;
	private Long dealerId;
	private String model;
	@NotEmpty
	private Double price;
	private String status;

	public VehicleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "VehicleDto [id=" + id + ", dealerId=" + dealerId + ", model=" + model + ", price=" + price + ", status="
				+ status + "]";
	}

}
