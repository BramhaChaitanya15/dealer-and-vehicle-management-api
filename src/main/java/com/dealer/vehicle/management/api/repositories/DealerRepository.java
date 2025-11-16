package com.dealer.vehicle.management.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dealer.vehicle.management.api.entities.Dealer;

// repository interface for accessing the jparepository methods
public interface DealerRepository extends JpaRepository<Dealer, Long> {
	Optional<Dealer> findByEmail(String email);
}
