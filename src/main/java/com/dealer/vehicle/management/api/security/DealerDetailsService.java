package com.dealer.vehicle.management.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dealer.vehicle.management.api.entities.Dealer;
import com.dealer.vehicle.management.api.repositories.DealerRepository;

@Service
public class DealerDetailsService implements UserDetailsService {
    @Autowired
    private DealerRepository dealerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Dealer dealer = dealerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Dealer not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(dealer.getEmail())
                .password(dealer.getPassword())
                .roles("DEALER")
                .build();
    }
}

