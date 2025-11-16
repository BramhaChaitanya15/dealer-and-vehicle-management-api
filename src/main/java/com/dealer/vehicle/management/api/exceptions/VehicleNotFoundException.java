package com.dealer.vehicle.management.api.exceptions;

//user defined class for vehicle exceptions
public class VehicleNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(String message) {
        super(message);
    }
	
}
