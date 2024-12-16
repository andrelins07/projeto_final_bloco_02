package com.generation.devfarma.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.generation.devfarma.model.Produto;
import com.generation.devfarma.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto) {

		return produtoService.cadastrarProduto(produto)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@GetMapping
	public ResponseEntity<List<Produto>> getAllProduto() {

		return ResponseEntity.ok(produtoService.listarTodosProdutos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {

		return produtoService.buscarProdutoPorId(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getProdutosByNome(@PathVariable String nome) {

		return ResponseEntity.ok(produtoService.buscarProdutoPorNome(nome));
	}

	@GetMapping("categoria/{categoria}")
	public ResponseEntity<List<Produto>> findProdutoByCategoria(@PathVariable String categoria) {
		return ResponseEntity.ok(produtoService.buscarPorCategoria(categoria));
	}

	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto) {

		return produtoService.atualizarProduto(produto).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable Long id) {

		produtoService.deletarProduto(id);

	}
}
