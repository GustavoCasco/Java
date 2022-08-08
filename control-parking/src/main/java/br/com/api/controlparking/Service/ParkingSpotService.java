package br.com.api.controlparking.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.api.controlparking.DTOs.ParkinkSpotDTO;
import br.com.api.controlparking.Model.ParkingSpotModel;
import br.com.api.controlparking.Repository.IParkingRepository;

@Service
public class ParkingSpotService {	
	
	@Autowired
	IParkingRepository repository;
	
	@Transactional
	public ParkingSpotModel	save(ParkingSpotModel model) {
		return repository.save(model);
	}
	
	public boolean existPlacaVeiculo(String placa) {
		return repository.existsByLicensePlate(placa);
	}
	
	public boolean existBlockAndApartament(String apartament, String block) {
		return repository.existsByApartamentAndBlock(apartament, block);
	}
	
	public boolean existBlockParkingNumber(String number) {
		return repository.existsByParkingSpotNumber(number);
	}
	
	public Page<ParkingSpotModel> getAll(Pageable page){
		return repository.findAll(page);
	}
	
	public Optional<ParkingSpotModel> getOne(UUID id)
	{
		return repository.findById(id);
	}
	
	@Transactional
	public void delete(UUID id)
	{
		repository.deleteById(id);
	}
}
