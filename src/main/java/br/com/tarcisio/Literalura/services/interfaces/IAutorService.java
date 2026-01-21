package br.com.tarcisio.Literalura.services.interfaces;

import java.util.List;

import br.com.tarcisio.Literalura.model.Autor;

public interface IAutorService {
    List<Autor> ListarTodosAutores();
    List<Autor> ListarAutoresVivosPeloAno(Integer anoBuscado);
}
