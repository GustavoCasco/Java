package br.com.springdata.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "cargos")
@Data
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCargo;
	private String descricao;
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionario;
	
	@Override
	public String toString() {
		return "Cargo {idCargo=" + idCargo + ", descricao=" + descricao + "}";
	}
}
