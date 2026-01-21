package br.com.tarcisio.Literalura.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.tarcisio.Literalura.model.Autor;
import br.com.tarcisio.Literalura.model.Livro;
import br.com.tarcisio.Literalura.repository.IAutorRepository;
import br.com.tarcisio.Literalura.repository.ILivroRepository;
import br.com.tarcisio.Literalura.services.interfaces.ILivroService;
import jakarta.transaction.Transactional;

@Service
public class LivroService implements ILivroService {

    private final ILivroRepository repositorio;
    private final IAutorRepository autorRepositorio;

    public LivroService(ILivroRepository repository, IAutorRepository repository2) {
        this.repositorio = repository;
        this.autorRepositorio = repository2;
    }

    @Override
    @Transactional
    public void PersistirLivro(Livro livro) {
        try {
            Livro livroVerficado = verificarExistenciaAutor(livro);
            repositorio.save(livroVerficado);
            repositorio.flush();
        } catch (Exception e) {
            throw e;
        }
    }

    private Livro verificarExistenciaAutor(Livro livro) {
        List<Autor> autoresGerenciados = new ArrayList<>();

        for (Autor autor : livro.getAutores()) {
            Autor existente = autorRepositorio
                .findByNome(autor.getNome())
                .orElseGet(() -> autorRepositorio.save(autor));

            autoresGerenciados.add(existente);
        }
     
        livro.setAutores(autoresGerenciados);
        return livro;
    }

    @Override
    @Transactional
    public List<Livro> ListarTodosLivros() {
        return repositorio.findAllComAutores();
    }

    @Override
    public List<Livro> ListarLivrosPorIdioma(String idomaBuscado) {
       return repositorio.findByIdioma(idomaBuscado);
    }

}
