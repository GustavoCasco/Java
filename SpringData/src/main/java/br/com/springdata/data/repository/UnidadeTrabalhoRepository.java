package br.com.springdata.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.springdata.data.model.Unidade;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<Unidade, Integer> {

}
