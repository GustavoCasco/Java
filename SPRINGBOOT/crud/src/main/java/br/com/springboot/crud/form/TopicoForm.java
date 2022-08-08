package br.com.springboot.crud.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.springboot.crud.model.Curso;
import br.com.springboot.crud.model.Topico;
import br.com.springboot.crud.repository.CursoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoForm {
	
	@NotEmpty
	@NotNull
	@NotBlank
	private String titulo;
	@NotEmpty
	@NotNull
	@NotBlank
	private String mensagem;
	@NotEmpty
	@NotNull
	@NotBlank
	private String nomeCurso;
	
	public Topico convert(CursoRepository curso) {
		Curso c = curso.findByNome(nomeCurso);
		
		Topico topico = new Topico();
		topico.setTitulo(this.titulo);
		topico.setMensagem(mensagem);
		topico.setCurso(c);
		
		return topico;
	}
}
