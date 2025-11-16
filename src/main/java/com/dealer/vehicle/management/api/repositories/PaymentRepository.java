package com.dealer.vehicle.management.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dealer.vehicle.management.api.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
