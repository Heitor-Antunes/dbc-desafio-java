package com.dbc.desafio.models.response;

import com.dbc.desafio.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRecomendadoResponse {

    private Produto produto;
}
