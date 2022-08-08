package br.com.springboot.crud.form;

import br.com.springboot.crud.model.Topico;
import br.com.springboot.crud.repository.TopicoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoFormTopico {
	private String titulo;
	private String message;
	
	@SuppressWarnings("deprecation")
	public Topico Atualizar(Long id, TopicoRepository repository) {
		Topico topico = repository.getById(id);
		topico.setTitulo(titulo);
		topico.setMensagem(message);
		return topico;
	}
}
