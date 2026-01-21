package br.com.tarcisio.Literalura.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "autores", uniqueConstraints = {
        @UniqueConstraint(name = "uk_autor_nome", columnNames = "nome")
})
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;

    @Column(nullable = true)
    private Integer anoFalecimento;
    @ManyToMany(mappedBy = "autores")
    private List<Livro> livros;

    public Autor() {
    }

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoNascimento();
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public List<String> getNomeLivros() {
        return livros.stream().map(l -> l.getTitulo()).toList();
    }

    @Override
    public String toString() {
        return "\n" +
                "Autor: " + this.nome + "\n" +
                "AnoNascimento: " + this.anoNascimento + "\n" +
                "AnoFalecimento: " + this.anoFalecimento + "\n" +
                "Livros: " + this.getNomeLivros() + "\n";
    }

}
