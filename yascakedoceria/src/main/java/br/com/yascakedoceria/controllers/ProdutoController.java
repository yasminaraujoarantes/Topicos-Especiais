package br.com.yascakedoceria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.yascakedoceria.model.Produto;
import br.com.yascakedoceria.services.ProdutoService;

@CrossOrigin
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste() {
		return ResponseEntity.ok().body("API Produtos funcionando !!!");
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(final @PathVariable("id") Long idProduto) throws Exception{
		service.deletarProduto(idProduto);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/inserir")
	@JsonView(View.Produto.class)
	public ResponseEntity<Long> inserir(@RequestBody Produto produtoView) throws Exception{
		Produto produto = service.inserirProduto(produtoView);
		return ResponseEntity.ok().body(produto.getId());
	}
	
	@PutMapping("/editar/{idProduto}")
	@JsonView(View.Produto.class)
	public ResponseEntity<Long> editar(@RequestBody Produto produtoView, @PathVariable("idProduto") Long idProduto) throws Exception {
		Produto produto = service.editarProduto(produtoView, idProduto);
		return  ResponseEntity.ok().body(produto.getId());
	}
	
	@GetMapping("/buscarProduto/{id}")
	@JsonView(View.ProdutoCompleto.class)
	public ResponseEntity<Produto> buscarProduto(@PathVariable("id") Long idProduto) {
		Produto produto = service.buscarProduto(idProduto);
		return  ResponseEntity.ok().body(produto);
	}
	
	@GetMapping("/buscarProdutoPorNomeQtd")
	@JsonView(View.ProdutoCompleto.class)
	public ResponseEntity<List<Produto>> buscarProdutoPorNomeQtd(@RequestParam("nome") String nomeProduto, @RequestParam("quantidade") double quantidade) {
		List<Produto> produto = service.buscarProdutoPorNomeQtd(nomeProduto, quantidade);
		return  ResponseEntity.ok().body(produto);
	}

}
