package br.com.tarcisio.Literalura.principal;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import br.com.tarcisio.Literalura.model.Autor;
import br.com.tarcisio.Literalura.model.DadosApi;
import br.com.tarcisio.Literalura.model.Livro;
import br.com.tarcisio.Literalura.services.ConsumoApi;
import br.com.tarcisio.Literalura.services.ConversorDados;
import br.com.tarcisio.Literalura.services.interfaces.IAutorService;
import br.com.tarcisio.Literalura.services.interfaces.ILivroService;

@Component
public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConversorDados conversor = new ConversorDados();
    private final ILivroService livroService;
    private final IAutorService autorService;

    public Principal(ILivroService livroservice, IAutorService autorService) {
        this.livroService = livroservice;
        this.autorService = autorService;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    -------------------
                    1 - Buscar livro pelo titulo
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros por idioma
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroTitulo();
                case 2:
                    listarTodosLivros();
                case 3:
                    listarTodosAutores();
                case 4:
                    listarAutoresVivosPeloAno();
                case 5:
                    listarLivrosPorIdioma();
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listarLivrosPorIdioma() {
        String idiomaSelecionado = "";
        System.out.println("Informe o idioma para fazer a buscar:" + "\n" +
                "es - Espanhol" + "\n" +
                "en - Inglês" + "\n" +
                "fr - Francês" + "\n" +
                "pt - Portugues");
        idiomaSelecionado = leitura.nextLine();

        List<Livro> livrosRetornados = livroService.ListarLivrosPorIdioma(idiomaSelecionado);
        if(livrosRetornados.isEmpty()){
            System.out.println("\n" + "----- NENHUM LIVRO ENCONTRADO PARA O IDIOMA BUSCADO -----" + "\n");
        }else{
             livrosRetornados.stream().forEach(l -> {
                System.out.println(l.toString());
            });
        }

        exibeMenu();
    }

    private void listarAutoresVivosPeloAno() {
        Integer anoDigitado = 0;
        System.out.println("Insira o ano que deseja pesquisar:");
        anoDigitado = leitura.nextInt();

        List<Autor> autoresVivos = autorService.ListarAutoresVivosPeloAno(anoDigitado);

        if (autoresVivos.isEmpty()) {
            System.out.println("\n" + "----- NENHUM AUTOR VIVO NO ANO BUSCADO -----" + "\n");
        } else {
            System.out.println("-----------------" + "\n");
            autoresVivos.stream().forEach(a -> {
                System.out.println(a.toString());
            });
            System.out.println("-----------------" + "\n");
        }

        exibeMenu();
    }

    private void listarTodosAutores() {
        List<Autor> autoresRegistrados = autorService.ListarTodosAutores();

        if (autoresRegistrados.isEmpty()) {
            System.out.println("\n" + "----- NENHUM AUTOR REGISTRADO -----" + "\n");
        } else {
            System.out.println("-----------------" + "\n");
            autoresRegistrados.stream().forEach(a -> {
                System.out.println(a.toString());
            });
            System.out.println("-----------------" + "\n");
        }

        exibeMenu();
    }

    private void listarTodosLivros() {
        List<Livro> livrosRegistrados = livroService.ListarTodosLivros();

        if (livrosRegistrados.isEmpty()) {
            System.out.println("\n" + "----- NENHUM LIVRO REGISTRADO -----" + "\n");
        } else {
            livrosRegistrados.stream().forEach(l -> {
                System.out.println(l.toString());
            });
        }

        exibeMenu();
    }

    private void buscarLivroTitulo() {
        String nomeLivro = "";
        System.out.println("Insira o nome do livro que deseja procurar:");
        nomeLivro = leitura.nextLine();
        String json = consumo.obterDados(nomeLivro);
        DadosApi dados = conversor.converterDados(json, DadosApi.class);

        if (verificarLivroExistente(dados)) {
            System.out.println("\n" + "----- LIVRO NAO ENCONTRADO -----" + "\n");
        } else {
            Livro livro = dados.livros().stream().map(l -> new Livro(l)).toList().get(0);
            PersistirLivro(livro);
            ExibirLivro(livro);
        }

        exibeMenu();
    }

    private void ExibirLivro(Livro livro) {
        System.out.println(livro.toString());
    }

    private void PersistirLivro(Livro livro) {
        livroService.PersistirLivro(livro);
    }

    private boolean verificarLivroExistente(DadosApi dados) {
        return dados.livros().isEmpty();
    }

}
