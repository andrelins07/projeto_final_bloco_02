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
import com.generation.devfarma.model.Categoria;
import com.generation.devfarma.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria) {

		return categoriaService.cadastrarCategoria(categoria)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> getAllCategorias() {

		return ResponseEntity.ok(categoriaService.listarTodasCategorias());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {

		return categoriaService.buscarUsuarioPorId(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> getCategoriasByNome(@PathVariable String nome){
		
		return ResponseEntity.ok(categoriaService.buscarCategoriasPorNome(nome));
	}

	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria) {

		return categoriaService.atualizarCategoria(categoria).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable Long id) {

		categoriaService.deletarUsuario(id);

	}
}
