package com.expox.tabelafipe.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosApiFipe {
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
    
    // Método estático para formatar uma lista de DadosApiFipe
    public static String formatarLista(List<DadosApiFipe> lista) {
        StringBuilder sb = new StringBuilder();
        for (DadosApiFipe dado : lista) {
            sb.append(dado.toString());
        }
        return sb.toString();
    }
}
