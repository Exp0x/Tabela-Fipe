package com.expox.tabelafipe.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosModelos {
    @JsonAlias("modelos")
    List<DadosApiFipe> dados;

    public List<DadosApiFipe> getDados() {
        return dados;
    }

    public void setDados(List<DadosApiFipe> dados) {
        this.dados = dados;
    }

    @Override
    public String toString() {
        return DadosApiFipe.formatarLista(dados);
    }
}
