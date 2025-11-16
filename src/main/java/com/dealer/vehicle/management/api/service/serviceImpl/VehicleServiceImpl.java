package com.dealer.vehicle.management.api.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealer.vehicle.management.api.entities.Dealer;
import com.dealer.vehicle.management.api.entities.Vehicle;
import com.dealer.vehicle.management.api.exceptions.DealerNotFoundException;
import com.dealer.vehicle.management.api.exceptions.VehicleNotFoundException;
import com.dealer.vehicle.management.api.helper.VehicleStatus;
import com.dealer.vehicle.management.api.payloads.VehicleDto;
import com.dealer.vehicle.management.api.repositories.DealerRepository;
import com.dealer.vehicle.management.api.repositories.VehicleRepository;
import com.dealer.vehicle.management.api.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private DealerRepository dealerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<VehicleDto> getAllVehicles() {
		return vehicleRepository.findAll().stream().map(this::vehicleToDto).collect(Collectors.toList());
	}

	@Override
	public VehicleDto getVehicleById(Long id) {
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with the id" + id));
		return vehicleToDto(vehicle);
	}

	@Override
	public VehicleDto createVehicle(VehicleDto dto) {
		Long dId = dto.getDealerId();
		Dealer dealer = dealerRepository.findById(dId)
				.orElseThrow(() -> new DealerNotFoundException("Dealer not found with id" + dId));
		Vehicle vehicle = new Vehicle();
		vehicle.setDealer(dealer);
		vehicle.setModel(dto.getModel());
		vehicle.setPrice(dto.getPrice());
		String statusStr = dto.getStatus(); // e.g. "AVAILABLE" or "SOLD"
		VehicleStatus statusType = VehicleStatus.valueOf(statusStr);
		vehicle.setStatus(statusType);
		Vehicle saved = vehicleRepository.save(vehicle);
		return vehicleToDto(saved);
	}

	@Override
	public VehicleDto updateVehicle(Long id, VehicleDto dto) {
		if (id == null) {
	        throw new IllegalArgumentException("Vehicle id must not be null");
	    }
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with the id" + id));
		Dealer dealer = dealerRepository.findById(dto.getDealerId())
				.orElseThrow(() -> new DealerNotFoundException("Dealer not found with id: " + dto.getDealerId()));
		vehicle.setDealer(dealer);
		vehicle.setModel(dto.getModel());
		vehicle.setPrice(dto.getPrice());
		String statusStr = dto.getStatus(); // e.g. "AVAILABLE" or "SOLD"
		VehicleStatus statusType = VehicleStatus.valueOf(statusStr);
		vehicle.setStatus(statusType);
		Vehicle saved = vehicleRepository.save(vehicle);
		return vehicleToDto(saved);
	}

	@Override
	public void deleteVehicle(Long id) {
		vehicleRepository.deleteById(id);
	}

	@Override
	public List<VehicleDto> getVehiclesByPremiumDealers() {
		return vehicleRepository.findVehiclesByPremiumDealers().stream().map(this::vehicleToDto)
				.collect(Collectors.toList());
	}

	// Conversion helpers
	// convert dto to entity
	private Vehicle dtoToVehicle(VehicleDto vehicleDto) {
		Vehicle Vehicle = this.modelMapper.map(vehicleDto, Vehicle.class);
		return Vehicle;
	}

	// converting entity to dto
	public VehicleDto vehicleToDto(Vehicle vehicle) {
		VehicleDto VehicleDto = this.modelMapper.map(vehicle, VehicleDto.class);
		return VehicleDto;
	}
}
