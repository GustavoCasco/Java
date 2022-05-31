package br.com.springmvc.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springmvc.loja.model.PedidoModel;
import br.com.springmvc.loja.model.enums.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

	List<PedidoModel> findByStatusPedido(StatusPedido statusPedido);
	
}
