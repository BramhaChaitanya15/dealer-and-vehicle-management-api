package com.dealer.vehicle.management.api.service;

import java.util.List;

import com.dealer.vehicle.management.api.payloads.DealerDto;

public interface DealerService {

	List<DealerDto> getAllDealers();

	DealerDto getDealerById(Long id);

	DealerDto createDealer(DealerDto dealerDto);

	DealerDto updateDealer(Long id, DealerDto dealerDto);

	void deleteDealer(Long id);

}
