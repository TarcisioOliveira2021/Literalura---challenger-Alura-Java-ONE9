package br.com.tarcisio.Literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosApi(  
    @JsonAlias("results") List<DadosLivro> livros) {
} 

