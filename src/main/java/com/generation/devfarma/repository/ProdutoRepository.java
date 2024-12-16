package com.generation.devfarma.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.generation.devfarma.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p FROM Produto p WHERE LOWER(p.categoria.nome) LIKE LOWER(CONCAT('%', :nomeCategoria, '%'))")
	List<Produto> buscarProdutosPorNomeCategoria(@Param("nomeCategoria") String nomeCategoria);

	public Optional<Produto> findProdutoByProduto(String nome);

	public List<Produto> findAllByProdutoContainingIgnoreCase(@Param("produto") String produto);

}
