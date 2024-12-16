package com.generation.devfarma.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.generation.devfarma.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public Optional<Categoria> findCategoriaByNome(String nome);

	public List<Categoria> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
