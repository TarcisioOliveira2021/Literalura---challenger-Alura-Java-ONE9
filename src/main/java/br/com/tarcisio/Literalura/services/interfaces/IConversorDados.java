package br.com.tarcisio.Literalura.services.interfaces;

public interface IConversorDados {
    <XPTO> XPTO converterDados(String json, Class<XPTO> classeConverter);
} 
