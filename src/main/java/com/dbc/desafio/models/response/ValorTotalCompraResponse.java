package com.dbc.desafio.models.response;

import com.dbc.desafio.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValorTotalCompraResponse {

    private Produto produto;
    private Integer quantidade;
    private BigDecimal valorTotal;
}
