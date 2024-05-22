package br.com.alura.tabelafipe.module;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarcas(@JsonAlias("codigo")String codigo,
                          @JsonAlias("nome")String nome
                          ) {
}
