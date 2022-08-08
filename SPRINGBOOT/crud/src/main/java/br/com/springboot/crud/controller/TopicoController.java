package br.com.springboot.crud.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.springboot.crud.DTO.TopicoDTO;
import br.com.springboot.crud.form.AtualizacaoFormTopico;
import br.com.springboot.crud.form.TopicoForm;
import br.com.springboot.crud.model.Topico;
import br.com.springboot.crud.repository.CursoRepository;
import br.com.springboot.crud.repository.TopicoRepository;

@RestController
@RequestMapping("topico")
public class TopicoController {
	
	@Autowired
	private TopicoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDTO> listarTopicos(){
		List<Topico> topico = repository.findAll();
		return TopicoDTO.convert(topico);
	}
	
	@GetMapping("{nome}")
	public List<TopicoDTO> listarTopicosporCurso(@PathVariable("nome")String nomeCurso){
		List<Topico> topico = repository.findByCursoNome(nomeCurso);
		return TopicoDTO.convert(topico);
	}
	
	@PostMapping("save")
	public ResponseEntity<TopicoDTO> cadastrarTopico(@RequestBody @Valid TopicoForm form, UriComponentsBuilder build) {
		Topico topico = form.convert(cursoRepository);
		repository.save(topico);
		
		URI uri = build.path("topico/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	@PutMapping("update/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizarTopico(@PathVariable 
			Long id, @RequestBody @Valid AtualizacaoFormTopico attTopico){

		Topico topico = attTopico.Atualizar(id, repository);
		return ResponseEntity.ok(new TopicoDTO(topico));
	}
	
	@DeleteMapping("delete/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> removerTopico(@PathVariable 
			Long id){
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
