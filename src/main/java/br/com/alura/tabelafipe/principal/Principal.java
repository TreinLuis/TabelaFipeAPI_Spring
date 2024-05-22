package br.com.alura.tabelafipe.principal;

import br.com.alura.tabelafipe.module.DadosMarcas;
import br.com.alura.tabelafipe.module.DadosModelos;
import br.com.alura.tabelafipe.service.ConsumoAPI;
import br.com.alura.tabelafipe.service.ConverteDados;
import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/carros/marcas" ;
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private Scanner in = new Scanner(System.in);



    public void exibeMenu() throws JsonProcessingException {
        ConverteDados converteDados = new ConverteDados();

        String uri = ENDERECO ;
        var json = consumoApi.obterDados(uri);
        List<DadosMarcas> dadosMarcas = converteDados.obterLista(json,DadosMarcas.class);
        dadosMarcas.stream()
                        .sorted(Comparator.comparing(DadosMarcas::codigo))
                                .forEach(System.out::println);

        System.out.println("Digite a marca do carro para iniciar a pesquisa: ");
        int marca = in.nextInt();
        in.nextLine();
        uri = ENDERECO + "/" + marca + "/modelos" ;
        json = consumoApi.obterDados(uri);
        var dadosModelos = converteDados.obterDados(json,DadosModelos.class);
        System.out.println("Modelos dessa marca: ");
        dadosModelos.modelos().stream()
                .sorted(Comparator.comparing(DadosMarcas::codigo))
                .forEach(System.out::println);
    }
}
