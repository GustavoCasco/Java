package br.com.springdata.data.model.dto;

import lombok.Data;

@Data
public class FuncionarioDto {
	private Integer Id;
	private String Nome;
	private Double Salario;
}
