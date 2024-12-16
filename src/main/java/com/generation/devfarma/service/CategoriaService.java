package com.generation.devfarma.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.generation.devfarma.model.Categoria;
import com.generation.devfarma.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Categoria> cadastrarCategoria(Categoria novaCategoria) {

		Optional<Categoria> categoria = categoriaRepository.findCategoriaByNome(novaCategoria.getNome());

		if (categoria.isEmpty()) {
			return Optional.of(categoriaRepository.save(novaCategoria));
		}
		return Optional.empty();
	}

	public List<Categoria> listarTodasCategorias() {

		return categoriaRepository.findAll();
	}

	public Optional<Categoria> buscarCategoriaPorId(Long id) {
		return categoriaRepository.findById(id);
	}

	public Optional<Categoria> atualizarCategoria(Categoria categoriaAtualizada) {

		if (categoriaRepository.existsById(categoriaAtualizada.getId())) {

			Optional<Categoria> categoria = categoriaRepository.findCategoriaByNome(categoriaAtualizada.getNome());

			if (categoria.isPresent() && categoria.get().getId() != categoriaAtualizada.getId()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome de categoria já existe!", null);
			}

			return Optional.of(categoriaRepository.save(categoriaAtualizada));
		}
		return Optional.empty();
	}

	public void deletarCategoriaPorId(Long id) {
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if(categoria.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		categoriaRepository.delete(categoria.get());
	}

	public List<Categoria> buscarCategoriasPorNome(String nome) {
		return categoriaRepository.findAllByNomeContainingIgnoreCase(nome);
	}
}
