package com.dealer.vehicle.management.api.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dealer.vehicle.management.api.entities.Dealer;
import com.dealer.vehicle.management.api.exceptions.DealerNotFoundException;
import com.dealer.vehicle.management.api.helper.SubscriptionType;
import com.dealer.vehicle.management.api.payloads.DealerDto;
import com.dealer.vehicle.management.api.repositories.DealerRepository;
import com.dealer.vehicle.management.api.service.DealerService;

@Service
public class DealerServiceImpl implements DealerService {

	@Autowired
	private DealerRepository dealerRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<DealerDto> getAllDealers() {
		return dealerRepository.findAll().stream().map(this::dealerToDto).collect(Collectors.toList());
	}

	@Override
	public DealerDto getDealerById(Long id) {
		Dealer dealer = dealerRepository.findById(id)
				.orElseThrow(() -> new DealerNotFoundException("Dealer not found with id: " + id));
		return dealerToDto(dealer);
	}

	@Override
	public DealerDto createDealer(DealerDto dto) {
		Dealer dealer = this.dtoToDealer(dto);
		String subscriptionStr = dto.getSubscriptionType(); // e.g. "BASIC" or "PREMIUM"
		SubscriptionType subscriptionType = SubscriptionType.valueOf(subscriptionStr);
		dealer.setSubscriptionType(subscriptionType);
		dealer.setPassword(this.bCryptPasswordEncoder.encode(dto.getPassword()));
		Dealer saved = dealerRepository.save(dealer);
		return dealerToDto(saved);
	}

	@Override
	public DealerDto updateDealer(Long id, DealerDto dto) {
		Dealer dealer = dealerRepository.findById(id)
				.orElseThrow(() -> new DealerNotFoundException("Dealer not found with id: " + id));
		dealer.setName(dto.getName());
		dealer.setEmail(dto.getEmail());
		String subscriptionStr = dto.getSubscriptionType(); // e.g. "BASIC" or "PREMIUM"
		SubscriptionType subscriptionType = SubscriptionType.valueOf(subscriptionStr);
		dealer.setSubscriptionType(subscriptionType);
		Dealer saved = dealerRepository.save(dealer);
		return dealerToDto(saved);
	}

	@Override
	public void deleteDealer(Long id) {
		dealerRepository.deleteById(id);
	}

	// Conversion helpers
	// convert dto to entity
	private Dealer dtoToDealer(DealerDto dealerDto) {
		Dealer dealer = this.modelMapper.map(dealerDto, Dealer.class);
		return dealer;
	}

	// converting entity to dto
	public DealerDto dealerToDto(Dealer dealer) {
		DealerDto DealerDto = this.modelMapper.map(dealer, DealerDto.class);
		return DealerDto;
	}
}
