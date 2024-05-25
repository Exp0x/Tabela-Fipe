package com.expox.tabelafipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Veiculo {
        @JsonAlias("Valor")
        String valor;
        @JsonAlias("Marca")
        String marca;
        @JsonAlias("AnoModelo")
        String anoModelo;
        @JsonAlias("Modelo")
        String modelo;
        @JsonAlias("Combustivel")
        String combustivel;
        @JsonAlias("CodigoFipe")
        String codigoFipe;
        
        public String getValor() {
                return valor;
        }
        public void setValor(String valor) {
                this.valor = valor;
        }
        public String getMarca() {
                return marca;
        }
        public void setMarca(String marca) {
                this.marca = marca;
        }
        public String getAnoModelo() {
                return anoModelo;
        }
        public void setAnoModelo(String anoModelo) {
                this.anoModelo = anoModelo;
        }
        public String getModelo() {
                return modelo;
        }
        public void setModelo(String modelo) {
                this.modelo = modelo;
        }
        public String getCombustivel() {
                return combustivel;
        }
        public void setCombustivel(String combustivel) {
                this.combustivel = combustivel;
        }
        public String getCodigoFipe() {
                return codigoFipe;
        }
        public void setCodigoFipe(String codigoFipe) {
                this.codigoFipe = codigoFipe;
        }
        @Override
        public String toString() {
                return "Valor: " + valor + ",Marca: " + marca + ", Ano: " + anoModelo + ", Modelo: "
                                + modelo + ", Combustivel: " + combustivel + ", CodigoFipe: " + codigoFipe + "\n";
        }

}
