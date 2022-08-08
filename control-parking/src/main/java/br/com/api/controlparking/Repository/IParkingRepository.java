package br.com.api.controlparking.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.controlparking.Model.ParkingSpotModel;

@Repository
public interface IParkingRepository extends JpaRepository<ParkingSpotModel, UUID> {

	boolean existsByLicensePlate(String license);
	boolean existsByApartamentAndBlock(String apartament, String block);
	boolean existsByParkingSpotNumber(String number);

}
