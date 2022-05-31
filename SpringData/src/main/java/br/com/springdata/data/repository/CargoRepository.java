package br.com.springdata.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.springdata.data.model.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}
