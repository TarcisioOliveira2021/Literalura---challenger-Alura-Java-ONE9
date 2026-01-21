package br.com.tarcisio.Literalura.services.interfaces;

import java.util.List;

import br.com.tarcisio.Literalura.model.Livro;

public interface ILivroService {
        void PersistirLivro(Livro livro);
        List<Livro> ListarTodosLivros();
        List<Livro> ListarLivrosPorIdioma(String idomaBuscado);
}
