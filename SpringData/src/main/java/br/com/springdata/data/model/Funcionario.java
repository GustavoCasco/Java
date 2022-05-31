package br.com.springdata.data.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Entity
@Data
@Table(name = "funcionarios")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFuncionario;
	private String nome;
	private String cpf;
	private Double salario;
	private LocalDate dataContratacao;
	@ManyToOne
	@JoinColumn(name = "idCargo", nullable = false)
	private Cargo cargo;
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionarios_unidades", joinColumns = {
			@JoinColumn(name = "fk_funcionario") }, 
	inverseJoinColumns = { @JoinColumn(name = "fk_unidade") })
	private List<Unidade> unidadeTrabalhos;
	
	@Override
	public String toString() {
		return "Funcionario [idFuncionario=" + idFuncionario + ", nome=" + nome + ", cpf=" + cpf + ", salario="
				+ salario + ", dataContratacao=" + dataContratacao + "]";
	}
}
