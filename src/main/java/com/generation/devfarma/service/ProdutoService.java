package com.generation.devfarma.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.generation.devfarma.model.Produto;
import com.generation.devfarma.repository.CategoriaRepository;
import com.generation.devfarma.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Produto> cadastrarProduto(Produto novoProduto) {

		Optional<Produto> produto = produtoRepository.findProdutoByProduto(novoProduto.getProduto());

		if (produto.isEmpty()) {

			if (categoriaRepository.existsById(novoProduto.getCategoria().getId())) {

				return Optional.of(produtoRepository.save(novoProduto));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada", null);
		}
		return Optional.empty();
	}

	public List<Produto> listarTodosProdutos() {

		return produtoRepository.findAll();
	}

	public Optional<Produto> buscarProdutoPorId(Long id) {
		return produtoRepository.findById(id);
	}

	public Optional<Produto> atualizarProduto(Produto produtoAtualizado) {

		if (produtoRepository.existsById(produtoAtualizado.getId())) {

			Optional<Produto> produto = produtoRepository.findProdutoByProduto(produtoAtualizado.getProduto());

			if (produto.isPresent() && produto.get().getId() != produtoAtualizado.getId())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um produto cadastrado com esse nome!", null);
			
			if (!categoriaRepository.existsById(produtoAtualizado.getCategoria().getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada", null);

			return Optional.of(produtoRepository.save(produtoAtualizado));
		}
		return Optional.empty();
	}

	public void deletarProduto(Long id) {

		Optional<Produto> categoria = produtoRepository.findById(id);

		if (categoria.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		produtoRepository.delete(categoria.get());
	}

	public List<Produto> buscarProdutoPorNome(String nome) {
		return produtoRepository.findAllByProdutoContainingIgnoreCase(nome);
	}

	public List<Produto> buscarPorCategoria(String categoria) {
		return produtoRepository.buscarProdutosPorNomeCategoria(categoria);
	}
}
