package com.expox.tabelafipe.principal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.expox.tabelafipe.models.DadosModelos;
import com.expox.tabelafipe.models.DadosVeiculo;
import com.expox.tabelafipe.models.DadosApiFipe;
import com.expox.tabelafipe.service.ConsumoAPI;
import com.expox.tabelafipe.service.ConverteDados;

public class Principal {

    final String API_FIPE = "https://parallelum.com.br/fipe/api/v1/";
    final String API_FIPE_MARCAS = "/marcas/";
    final String API_FIPE_MODELOS = "/modelos/";
    final String API_FIPE_ANOS = "/anos/";
    final String[] TIPOS = { "carros", "motos", "caminhoes" };
    String busca;
    Scanner scanner = new Scanner(System.in);
    ConsumoAPI api = new ConsumoAPI();
    ConverteDados conversor = new ConverteDados();
    String json;

    // De certa forma, o projeto ta pronto, mas tem como melhorar MUITO
    // TODO: Iniciar o git(parabens idiota, só no final lembrou do git)| Refatorar o
    // codigo todo (mover certas coisas para métodos e adicionar Interfaces) |
    // Excluir DadosModelos(o motivo pra existencia dessa classe é muito porca,
    // procurar na docs do Jackson como resolver) | fazer um loop pra pesquisa final
    // (provavelmente dentro de um método) | Formatar melhor a pesquisa final |
    // Dar nomes melhores as variaveis e padronizados
    public void start() {
        System.out.println("Digite o tipo de veiculo: carros/motos/caminhoes \n Ou digite SAIR para fechar o programa");
        String tipo;
        while (true) {
            tipo = scanner.nextLine().toLowerCase();
            if (tipo.contains("sair")) {
                System.out.println("Programa encerrado");
                return;
            }
            if (Arrays.asList(TIPOS).contains(tipo)) {
                break;
            }
            System.out.println("Tipo digitado errado");
        }
        busca = API_FIPE + tipo + API_FIPE_MARCAS;
        json = api.obterDados(busca);
        List<DadosApiFipe> marcas = conversor.obterListaDados(json, DadosApiFipe.class);
        marcas.forEach(System.out::println);

        System.out.println("Digite o código da empresa que deseja pesquisar \n Ou digite SAIR para fechar o programa");
        String codigoEmpresa;
        while (true) {
            String codigoEmpresaDigitado;
            codigoEmpresaDigitado = scanner.nextLine().toLowerCase();

            if (codigoEmpresaDigitado.equals("sair")) {
                System.out.println("Programa encerrado");
                return;
            }
            boolean encontrado = marcas.stream().anyMatch(m -> m.getCodigo().equals(codigoEmpresaDigitado));
            if (encontrado) {
                codigoEmpresa = codigoEmpresaDigitado;
                break;
            }
            System.out.println("Código digitado não existente");
        }
        busca = busca + codigoEmpresa + API_FIPE_MODELOS;
        json = api.obterDados(busca);
        DadosModelos modelos = conversor.obterDados(json, DadosModelos.class);

        System.out.println(modelos);
        System.out.println("Digite um TRECHO do NOME do veículo para consulta");
        String nome = scanner.nextLine().toLowerCase();

        List<DadosApiFipe> possiveisVeiculos = modelos.getDados().stream()
                .filter(d -> d.getDescritor().toLowerCase().contains(nome))
                .collect(Collectors.toList());
        System.out.println(DadosApiFipe.formatarLista(possiveisVeiculos));
        System.out.println("Digite o CÓDIGO do modelo para consulta");
        String codigoModelo = scanner.nextLine();

        busca = busca + codigoModelo + API_FIPE_ANOS;
        json = api.obterDados(busca);
        List<DadosApiFipe> listaAnos = conversor.obterListaDados(json, DadosApiFipe.class);

        System.out.println(listaAnos);
        List<DadosVeiculo> listaVeiculos = listaAnos.stream().map(d -> {
            String codigo = d.getCodigo();
            json = api.obterDados(busca + codigo);
            return conversor.obterDados(json, DadosVeiculo.class);
        })
                .collect(Collectors.toList());

        System.out.println(listaVeiculos);
    }
}
