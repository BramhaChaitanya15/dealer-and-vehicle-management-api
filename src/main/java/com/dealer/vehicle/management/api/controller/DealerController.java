package com.dealer.vehicle.management.api.controller;

import java.util.List;

import com.dealer.vehicle.management.api.payloads.DealerDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealer.vehicle.management.api.service.DealerService;

@RestController
@RequestMapping("/api/dealers")
public class DealerController {

	@Autowired
	private DealerService dealerService;

	@GetMapping
	public List<DealerDto> getAllDealers() {
		return dealerService.getAllDealers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DealerDto> getDealer(@PathVariable Long id) {
		DealerDto dealer = dealerService.getDealerById(id);
		if (dealer != null) {
			return ResponseEntity.ok(dealer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DealerDto> createDealer(@RequestBody DealerDto DealerDto) {
		DealerDto createdDealer = dealerService.createDealer(DealerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDealer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DealerDto> updateDealer(@PathVariable Long id, @RequestBody DealerDto DealerDto) {
		DealerDto updatedDealer = dealerService.updateDealer(id, DealerDto);
		if (updatedDealer != null) {
			return ResponseEntity.ok(updatedDealer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDealer(@PathVariable Long id) {
		dealerService.deleteDealer(id);
		return ResponseEntity.noContent().build();
	}
}
