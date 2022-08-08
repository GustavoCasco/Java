package br.com.api.controlparking.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import br.com.api.controlparking.DTOs.ParkinkSpotDTO;
import br.com.api.controlparking.Model.ParkingSpotModel;
import br.com.api.controlparking.Service.ParkingSpotService;

@RestController
@RequestMapping("/parking")
public class ParkingSpotController {

	@Autowired
	ParkingSpotService parkingService;

	@PostMapping("/save-parking")
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkinkSpotDTO dto){
		if(parkingService.existBlockAndApartament(dto.getApartament(), dto.getBlock())){
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Ja existe vaga relacionada a esse bloco e esse apartamento");
		}
		
		if(parkingService.existBlockParkingNumber(dto.getParkingSpotNumber()))
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Numero de vaga vinculada a uma pessoa");
		}
		
		if(parkingService.existPlacaVeiculo(dto.getLicensePlate()))
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Numero de placa vinculada a uma pessoa");
		}
		
		var parkingSpot = new ParkingSpotModel();
		BeanUtils.copyProperties(dto, parkingSpot);	
		parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.save(parkingSpot));
	}
	
	@GetMapping()
	public ResponseEntity<Page<ParkingSpotModel>> getAll(@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.ASC) Pageable page){
		return ResponseEntity.ok(parkingService.getAll(page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOne(@PathVariable(value = "id")UUID id)
	{
		Optional<ParkingSpotModel> oneModel = parkingService.getOne(id);
		
		if(!oneModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(oneModel.get().toDto());
	}
	
	
	@DeleteMapping("remove/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id")UUID id)
	{
		Optional<ParkingSpotModel> oneModel = parkingService.getOne(id);
		
		if(!oneModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
		}
		
		parkingService.delete(oneModel.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso");
	}
	
	@PutMapping("edit/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id")UUID id,
										@RequestBody @Valid ParkinkSpotDTO dto)
	{
		Optional<ParkingSpotModel> oneModel = parkingService.getOne(id);
		
		if(!oneModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
		}
		var parkingModel = new ParkingSpotModel();
		BeanUtils.copyProperties(dto, parkingModel);
		parkingModel.setId(oneModel.get().getId());
		parkingModel.setRegistrationDate(oneModel.get().getRegistrationDate());
		return ResponseEntity.status(HttpStatus.OK).body(parkingService.save(parkingModel));
	}


	
}
