package com.dealer.vehicle.management.api.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class DealerDto {

	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String subscriptionType;

	public Long getId() {
		return id;
	}

	public DealerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	@Override
	public String toString() {
		return "DealerDto [id=" + id + ", name=" + name + ", email=" + email + ", subscriptionType=" + subscriptionType
				+ "]";
	}
}
