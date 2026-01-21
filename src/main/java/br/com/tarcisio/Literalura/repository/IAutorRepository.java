package br.com.tarcisio.Literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tarcisio.Literalura.model.Autor;

public interface IAutorRepository extends JpaRepository<Autor,Long>{

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.livros")
    List<Autor> findAllComNomeLivros();

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento >= :anoBuscado AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :anoBuscado)")
    List<Autor> findAutoresVivosPeloAno(Integer anoBuscado);

    Optional<Autor> findByNome(String nome);
}
