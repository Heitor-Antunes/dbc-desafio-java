package com.dbc.desafio.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompraResponse {

    private String nome;
    private String cpf;
    private List<ValorTotalCompraResponse> compras;
}
