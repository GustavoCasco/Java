package br.com.springboot.crud.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.springboot.crud.model.Topico;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TopicoDTO {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	
	public TopicoDTO (Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	
	public TopicoDTO() {
	}
	
	public static List<TopicoDTO> convert (List<Topico> list){
		return list.stream().map(TopicoDTO::new).collect(Collectors.toList());
	}

}
