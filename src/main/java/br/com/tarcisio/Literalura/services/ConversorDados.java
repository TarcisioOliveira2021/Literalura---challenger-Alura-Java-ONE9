package br.com.tarcisio.Literalura.services;

import br.com.tarcisio.Literalura.services.interfaces.IConversorDados;
import tools.jackson.databind.ObjectMapper;

public class ConversorDados implements IConversorDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converterDados(String json, Class<T> classeConverter) {
        try {
            return mapper.readValue(json, classeConverter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
