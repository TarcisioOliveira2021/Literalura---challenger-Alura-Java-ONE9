package br.com.tarcisio.Literalura.services;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


public class ConsumoApi {
        
    public String obterDados(String titulo){
        String tituloConvertido = FormatarTextoURLencode(titulo);
        String url = "https://gutendex.com/books?search="+ tituloConvertido;

        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .build();
        
        HttpResponse<String> resp = null;
        
        try{
            resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        String json = resp.body();
        return json;
    }

    public String FormatarTextoURLencode(String texto){
        return URLEncoder.encode(texto,StandardCharsets.UTF_8).toString();
    }
}
