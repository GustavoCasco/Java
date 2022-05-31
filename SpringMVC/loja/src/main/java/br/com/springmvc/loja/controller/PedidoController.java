package br.com.springmvc.loja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springmvc.loja.model.PedidoModel;
import br.com.springmvc.loja.model.dto.ProdutoRequestDTO;
import br.com.springmvc.loja.model.enums.StatusPedido;
import br.com.springmvc.loja.repository.PedidoRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository repository;

	@GetMapping()
	public String listarPedido(Model model) {
		
		List<PedidoModel> pedidos = repository.findAll();
		model.addAttribute("pedidos", pedidos);
		
		return "pedido/listarpedido";
	}
	
	@GetMapping("{statusPedido}")
	public String porStatus(@PathVariable("statusPedido") String statusPedido, Model model) {
		
		List<PedidoModel> pedidos = repository.findByStatusPedido(StatusPedido.valueOf(statusPedido.toUpperCase()));
		model.addAttribute("pedidos", pedidos);
		
		return "pedido/listarpedido";
	}
	@GetMapping("form")
	public String exibirTelaSalvarPedido(ProdutoRequestDTO request) {
		return "pedido/salvarPedido";
	}
	
	@PostMapping("salvar")
	public String salvarPedido(@Valid ProdutoRequestDTO request, BindingResult result) {
		
		if (result.hasErrors()) {
			return "pedido/salvarPedido";
		}
		
		PedidoModel newPedido = request.toModel();
		repository.save(newPedido);
		
		return "pedido/listarpedido";
	}

}
