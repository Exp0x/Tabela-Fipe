package com.expox.tabelafipe.principal;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.expox.tabelafipe.models.Modelos;
import com.expox.tabelafipe.models.Veiculo;
import com.expox.tabelafipe.models.Dados;
import com.expox.tabelafipe.service.ConsumoAPI;
import com.expox.tabelafipe.service.ConverteDados;

public class Principal {

    final String URL_API_FIPE = "https://parallelum.com.br/fipe/api/v1/";
    final String[] TIPOS = { "carros", "motos", "caminhoes" };
    String endereco;
    Scanner scanner = new Scanner(System.in);
    ConsumoAPI api = new ConsumoAPI();
    ConverteDados conversor = new ConverteDados();
    String json;

    public void start() {

        // Tipo veiculo
        System.out.println("Digite o tipo de veiculo: carros/motos/caminhoes \n Ou digite SAIR para fechar o programa");
        String tipo = scanner.nextLine().toLowerCase();
        endereco = URL_API_FIPE + tipo + "/marcas/";
        json = api.obterDados(endereco);
        List<Dados> marcas = conversor.obterListaDados(json, Dados.class);
        marcas.forEach(System.out::println);

        // Codigo marca
        System.out.println("Digite o código da marca que deseja pesquisar");
        String codigoMarca = scanner.nextLine().toLowerCase();
        endereco = endereco + codigoMarca + "/modelos/";
        json = api.obterDados(endereco);
        Modelos listaModelos = conversor.obterDados(json, Modelos.class);
        System.out.println("\nModelos dessa marca: ");
        listaModelos.modelos().stream().forEach(System.out::println);

        // Nome modelo
        System.out.println("\nDigite um TRECHO do NOME do veículo para consulta");
        String nomeVeiculo = scanner.nextLine().toLowerCase();
        List<Dados> modelosFiltrados = listaModelos.modelos().stream()
                .filter(m -> m.getDescritor().toLowerCase().contains(nomeVeiculo))
                .collect(Collectors.toList());
        modelosFiltrados.forEach(System.out::println);

        // Codigo modelo
        System.out.println("Digite o CÓDIGO do modelo para consulta");
        String codigoModelo = scanner.nextLine();
        endereco = endereco + codigoModelo + "/anos/";
        json = api.obterDados(endereco);
        List<Dados> anos = conversor.obterListaDados(json, Dados.class);
        List<Veiculo> listaVeiculos = anos.stream()
                .map(d -> {
                    String codigo = d.getCodigo();
                    json = api.obterDados(endereco + codigo);
                    return conversor.obterDados(json, Veiculo.class);
                })
                .collect(Collectors.toList());

        // resultado
        listaVeiculos.forEach(System.out::println);
    }
}
