package com.dealer.vehicle.management.api.service;

import java.util.List;

import com.dealer.vehicle.management.api.payloads.VehicleDto;

public interface VehicleService {

	List<VehicleDto> getAllVehicles();

	VehicleDto getVehicleById(Long id);

	VehicleDto createVehicle(VehicleDto vehicleDto);

	VehicleDto updateVehicle(Long id, VehicleDto vehicleDto);

	void deleteVehicle(Long id);

	List<VehicleDto> getVehiclesByPremiumDealers();

}
