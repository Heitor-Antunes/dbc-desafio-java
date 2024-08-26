package com.dbc.desafio.mocks;

import com.dbc.desafio.models.Cliente;
import com.dbc.desafio.models.Compra;
import com.dbc.desafio.models.Produto;
import com.dbc.desafio.models.response.CompraResponse;
import com.dbc.desafio.models.response.ProdutoRecomendadoResponse;
import com.dbc.desafio.models.response.RecomendacaoResponse;
import com.dbc.desafio.models.response.ValorTotalCompraResponse;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Mocks {

    public static List<Produto> mockProdutos() {
        return List.of(
                mockTinto(),
                mockBranco(),
                mockRose()
        );
    }

    public static List<Cliente> mockClientes() {
        return List.of(
                Cliente.builder()
                        .nome("Geraldo Pedro Julio Nascimento")
                        .cpf("05870189179")
                        .compras(List.of(
                                Compra.builder()
                                        .codigo("1")
                                        .quantidade(5)
                                        .build(),
                                Compra.builder()
                                        .codigo("2")
                                        .quantidade(4)
                                        .build(),
                                Compra.builder()
                                        .codigo("3")
                                        .quantidade(6)
                                        .build()))
                        .build(),
                Cliente.builder()
                        .nome("Vitória Alícia Mendes")
                        .cpf("20623850567")
                        .compras(List.of(
                                Compra.builder()
                                        .codigo("1")
                                        .quantidade(8)
                                        .build()))
                        .build(),
                Cliente.builder()
                        .nome("Fabiana Melissa Nunes")
                        .cpf("824643755772")
                        .compras(List.of(
                                Compra.builder()
                                        .codigo("2")
                                        .quantidade(1)
                                        .build()))
                        .build(),
                Cliente.builder()
                        .nome("Teresinha Daniela Galvão")
                        .cpf("04372012950")
                        .compras(List.of(
                                Compra.builder()
                                        .codigo("1")
                                        .quantidade(1)
                                        .build(),
                                Compra.builder()
                                        .codigo("2")
                                        .quantidade(3)
                                        .build()))
                        .build()
        );
    }

    public static List<CompraResponse> mockComprasResponsesOrdenada() {
        return List.of(
                CompraResponse.builder()
                        .nome("Geraldo Pedro Julio Nascimento")
                        .cpf("05870189179")
                        .compras(List.of(
                                ValorTotalCompraResponse.builder()
                                        .produto(mockTinto())
                                        .quantidade(5)
                                        .valorTotal(BigDecimal.valueOf(500))
                                        .build(),
                                ValorTotalCompraResponse.builder()
                                        .produto(mockBranco())
                                        .quantidade(4)
                                        .valorTotal(BigDecimal.valueOf(600))
                                        .build(),
                                ValorTotalCompraResponse.builder()
                                        .produto(mockRose())
                                        .quantidade(6)
                                        .valorTotal(BigDecimal.valueOf(1200))
                                        .build()))
                        .build(),
                CompraResponse.builder()
                        .nome("Vitória Alícia Mendes")
                        .cpf("20623850567")
                        .compras(List.of(
                                ValorTotalCompraResponse.builder()
                                        .produto(mockTinto())
                                        .quantidade(8)
                                        .valorTotal(BigDecimal.valueOf(800))
                                        .build()))
                        .build(),
                CompraResponse.builder()
                        .nome("Fabiana Melissa Nunes")
                        .cpf("824643755772")
                        .compras(List.of(
                                ValorTotalCompraResponse.builder()
                                        .produto(mockBranco())
                                        .quantidade(1)
                                        .valorTotal(BigDecimal.valueOf(150))
                                        .build()))
                        .build(),
                CompraResponse.builder()
                        .nome("Teresinha Daniela Galvão")
                        .cpf("04372012950")
                        .compras(List.of(
                                ValorTotalCompraResponse.builder()
                                        .produto(mockTinto())
                                        .quantidade(1)
                                        .valorTotal(BigDecimal.valueOf(100))
                                        .build(),
                                ValorTotalCompraResponse.builder()
                                        .produto(mockBranco())
                                        .quantidade(3)
                                        .valorTotal(BigDecimal.valueOf(450))
                                        .build()))
                        .build()
        );
    }

    public static List<CompraResponse> mockComprasResponsesOrdenadaMaiorCompra() {
        return List.of(
                CompraResponse.builder()
                        .nome("Geraldo Pedro Julio Nascimento")
                        .cpf("05870189179")
                        .compras(List.of(
                                ValorTotalCompraResponse.builder()
                                        .produto(mockBranco())
                                        .quantidade(4)
                                        .valorTotal(BigDecimal.valueOf(600))
                                        .build()))
                        .build(),
                CompraResponse.builder()
                        .nome("Vitória Alícia Mendes")
                        .cpf("20623850567")
                        .compras(Collections.emptyList())
                        .build(),
                CompraResponse.builder()
                        .nome("Fabiana Melissa Nunes")
                        .cpf("824643755772")
                        .compras(List.of(
                                ValorTotalCompraResponse.builder()
                                        .produto(mockBranco())
                                        .quantidade(1)
                                        .valorTotal(BigDecimal.valueOf(150))
                                        .build()))
                        .build(),
                CompraResponse.builder()
                        .nome("Teresinha Daniela Galvão")
                        .cpf("04372012950")
                        .compras(List.of(
                                ValorTotalCompraResponse.builder()
                                        .produto(mockBranco())
                                        .quantidade(3)
                                        .valorTotal(BigDecimal.valueOf(450))
                                        .build()))
                        .build()
        );
    }

    public static CompraResponse mockMaiorCompraAno() {
        return CompraResponse.builder()
                .nome("Geraldo Pedro Julio Nascimento")
                .cpf("05870189179")
                .compras(List.of(
                        ValorTotalCompraResponse.builder()
                                .produto(mockRose())
                                .quantidade(6)
                                .valorTotal(BigDecimal.valueOf(1200))
                                .build()))
                .build();
    }

    public static List<RecomendacaoResponse> mockRecomendacaoResponse() {
        return List.of(RecomendacaoResponse.builder()
                        .nome("Geraldo Pedro Julio Nascimento")
                        .cpf("05870189179")
                        .recomendacao(mockProdutoResponseVinhoTinto())
                        .build(),
                RecomendacaoResponse.builder()
                        .nome("Vitória Alícia Mendes")
                        .cpf("20623850567")
                        .recomendacao(mockProdutoResponseVinhoTinto())
                        .build(),
                RecomendacaoResponse.builder()
                        .nome("Fabiana Melissa Nunes")
                        .cpf("824643755772")
                        .recomendacao(mockProdutoResponseVinhoBranco())
                        .build(),
                RecomendacaoResponse.builder()
                        .nome("Teresinha Daniela Galvão")
                        .cpf("04372012950")
                        .recomendacao(mockProdutoResponseVinhoBranco())
                        .build());
    }

    private static ProdutoRecomendadoResponse mockProdutoResponseVinhoTinto() {
        return ProdutoRecomendadoResponse.builder()
                .produto(mockTinto())
                .build();
    }

    private static ProdutoRecomendadoResponse mockProdutoResponseVinhoBranco() {
        return ProdutoRecomendadoResponse.builder()
                .produto(mockTinto())
                .build();
    }

    public static ProdutoRecomendadoResponse mockProdutoResponseVinhoRose() {
        return ProdutoRecomendadoResponse.builder()
                .produto(mockRose())
                .build();
    }

    private static Produto mockTinto() {
        return Produto.builder()
                .codigo(1)
                .tipoVinho("Tinto")
                .preco(BigDecimal.valueOf(100))
                .safra("2018")
                .anoCompra(2021)
                .build();
    }

    private static Produto mockBranco() {
        return Produto.builder()
                .codigo(2)
                .tipoVinho("Branco")
                .preco(BigDecimal.valueOf(150))
                .safra("2019")
                .anoCompra(2022)
                .build();
    }

    public static Produto mockRose() {
        return Produto.builder()
                .codigo(3)
                .tipoVinho("Rosé")
                .preco(BigDecimal.valueOf(200))
                .safra("2020")
                .anoCompra(2023)
                .build();
    }

}