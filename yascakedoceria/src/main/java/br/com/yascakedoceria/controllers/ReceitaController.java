package br.com.yascakedoceria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.yascakedoceria.model.Receita;
import br.com.yascakedoceria.services.ReceitaService;

@CrossOrigin
@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {
	
	@Autowired
	private ReceitaService service;
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste() {
		return ResponseEntity.ok().body("API Receitas funcionando !!!");
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(final @PathVariable("id") Long idReceita) throws Exception{
		service.deletarReceita(idReceita);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/inserir")
	@JsonView(View.Receita.class)
	public ResponseEntity<Long> inserir(@RequestBody Receita receitaView) throws Exception{
		Receita Receita = service.inserirReceita(receitaView);
		return ResponseEntity.ok().body(Receita.getId());
	}
	
	@GetMapping("/buscarReceita/{id}")
	@JsonView(View.ReceitaCompleta.class)
	public ResponseEntity<Receita> buscarReceita(@PathVariable("id") Long idReceita) {
		Receita receita = service.buscarReceita(idReceita);
		return  ResponseEntity.ok().body(receita);
	}

}
