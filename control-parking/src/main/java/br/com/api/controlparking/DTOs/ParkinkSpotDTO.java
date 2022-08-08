package br.com.api.controlparking.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ParkinkSpotDTO {
	
	@NotBlank
	private String parkingSpotNumber;
	
	@NotBlank
	@Size(max = 7)
	private String licensePlate;
	
	@NotBlank
	private String brandCar;
	
	@NotBlank
	private String modelCar;
	
	@NotBlank
	private String colorCar;

	@NotBlank
	private String responsibleName;
	
	@NotBlank
	private String apartament;
	
	@NotBlank
	private String block;

}
