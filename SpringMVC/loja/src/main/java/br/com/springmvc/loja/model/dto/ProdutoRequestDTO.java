package br.com.springmvc.loja.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.springmvc.loja.model.PedidoModel;
import br.com.springmvc.loja.model.enums.StatusPedido;

public class ProdutoRequestDTO {
	
	@NotBlank
	private String nomeProduto;
	private String valorNegociado;
	private String dataEntrega;
	@NotBlank
	private String urlProduto;
	@NotBlank
	private String urlImage;
	private String descricaoProduto;

	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getValorNegociado() {
		return valorNegociado;
	}
	public void setValorNegociado(String valorNegociado) {
		this.valorNegociado = valorNegociado;
	}
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getUrlProduto() {
		return urlProduto;
	}
	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public PedidoModel toModel() {
		PedidoModel pedido = new PedidoModel();
		pedido.setDataEntrega(LocalDate.now());
		pedido.setDescricaoProduto(descricaoProduto);
		pedido.setNomeProduto(nomeProduto);
		pedido.setUrlImage(urlImage);
		pedido.setUrlProduto(urlProduto);
		pedido.setValorNegociado((new BigDecimal(valorNegociado)));
		pedido.setStatusPedido(StatusPedido.AGUARDANDO);
		return pedido;
	}
}