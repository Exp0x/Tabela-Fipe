package com.expox.tabelafipe.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> obterListaDados(String json, Class<T> classe) {
        try {
            //cria um CollectionType que descreve List<T>, onde T é a classe fornecida.
            // Resumindo: Estou criando uma referencia do tipo List<classe>
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, classe);
            return mapper.readValue(json, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
