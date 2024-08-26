package com.dbc.desafio.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecomendacaoResponse {

    private String nome;
    private String cpf;
    private ProdutoRecomendadoResponse recomendacao;
}
