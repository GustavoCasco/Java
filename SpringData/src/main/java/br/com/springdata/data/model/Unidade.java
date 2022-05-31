package br.com.springdata.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "unidades")
public class Unidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUnidade;
	
	private String descricao;
	private String endereco;
	
    @ManyToMany(mappedBy = "unidadeTrabalhos", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

}
