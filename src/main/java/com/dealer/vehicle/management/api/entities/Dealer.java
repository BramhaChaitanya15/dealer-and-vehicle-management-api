package com.dealer.vehicle.management.api.entities;

import com.dealer.vehicle.management.api.helper.SubscriptionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//mark the class as entity
@Entity
//set table name
@Table(name = "dealers")
public class Dealer {
	//primary key with auto incremented id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //column configurations
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "Email", nullable = false, unique = true)
    private String email;
    
    private String password;

    // used enum for fixed list of options
    // can be managed in frontend
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

	public Dealer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	@Override
	public String toString() {
		return "Dealer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", subscriptionType=" + subscriptionType + "]";
	}

}


