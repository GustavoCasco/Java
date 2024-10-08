package br.com.springboot.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.crud.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
	
	List<Topico> findByCursoNome(String nome);

}
