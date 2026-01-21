package br.com.tarcisio.Literalura.model;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToMany()
    @JoinTable(
        name = "livro_autor", 
        joinColumns = @JoinColumn(name = "livro_id"), 
        inverseJoinColumns = @JoinColumn(name = "autor_id"), 
        uniqueConstraints = {
            @UniqueConstraint(columnNames = { "livro_id", "autor_id" })
        }
    )
    private List<Autor> autores;
    private String idioma;
    private Double countDownloads;

    public Livro() {
    }

    public Livro(DadosLivro conversao) {
        this.titulo = conversao.titulo();
        this.autores = conversao.autores().stream().map(a -> new Autor(a)).toList();
        this.idioma = conversao.idiomas().get(0);
        this.countDownloads = conversao.countDowload();
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autoresNovos){
        this.autores = autoresNovos;
    }

    public String getIdiomas() {
        return this.idioma;
    }

    public Double getCountDownloads() {
        return countDownloads;
    }

    @Override
    public String toString() {
        return "\n" +
                "----- LIVRO -----" + "\n" +
                "Titulo: " + this.titulo + "\n" +
                "Autores: " + this.autores.stream().map(a -> a.getNome()).collect(Collectors.joining(", ")) + "\n" +
                "Idiomas: " + this.idioma + "\n" +
                "Número de Downloads: " + this.countDownloads + "\n" +
                "-----------------" + "\n";
    }

}
