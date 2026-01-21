package br.com.tarcisio.Literalura.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.tarcisio.Literalura.model.Autor;
import br.com.tarcisio.Literalura.repository.IAutorRepository;
import br.com.tarcisio.Literalura.services.interfaces.IAutorService;
import jakarta.transaction.Transactional;

@Service
public class AutorService implements IAutorService{

    private final IAutorRepository repositorio;

    public AutorService (IAutorRepository repo){
        this.repositorio = repo;
    }

    @Override
    @Transactional
    public List<Autor> ListarTodosAutores() {
     return repositorio.findAllComNomeLivros();
    }


    @Override
    @Transactional
    public List<Autor> ListarAutoresVivosPeloAno(Integer anoBuscado) {
        return repositorio.findAutoresVivosPeloAno(anoBuscado);
    }

}
