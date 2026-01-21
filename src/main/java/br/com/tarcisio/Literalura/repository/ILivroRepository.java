package br.com.tarcisio.Literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tarcisio.Literalura.model.Livro;


public interface ILivroRepository extends JpaRepository<Livro,Long>{
    @Query("SELECT DISTINCT l FROM Livro l LEFT JOIN FETCH l.autores")
    List<Livro> findAllComAutores();

    @Query("SELECT DISTINCT l FROM Livro l LEFT JOIN FETCH l.autores WHERE l.idioma = :idioma")
    List<Livro> findByIdioma(String idioma);
    
} 
