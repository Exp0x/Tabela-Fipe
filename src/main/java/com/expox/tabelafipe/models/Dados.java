package com.expox.tabelafipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dados {
    @JsonAlias("codigo")
    private String codigo;
    @JsonAlias("nome")
    private String descritor;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescritor() {
        return descritor;
    }

    public void setDescritor(String nome) {
        this.descritor = nome;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + "  Descrição: " + descritor + "\n";
    }
    
}
