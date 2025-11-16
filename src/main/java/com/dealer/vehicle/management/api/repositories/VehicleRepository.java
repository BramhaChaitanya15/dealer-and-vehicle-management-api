package com.dealer.vehicle.management.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dealer.vehicle.management.api.entities.Vehicle;

//repository interface for accessing the jparepository methods
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	// Custom query to find vehicles of PREMIUM dealers
	@Query("SELECT v FROM Vehicle v WHERE v.dealer.subscriptionType = 'PREMIUM'")
	List<Vehicle> findVehiclesByPremiumDealers();

}
