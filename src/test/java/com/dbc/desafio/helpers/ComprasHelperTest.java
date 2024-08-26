package com.dbc.desafio.helpers;

import com.dbc.desafio.models.Cliente;
import com.dbc.desafio.models.Produto;
import com.dbc.desafio.models.response.CompraResponse;
import com.dbc.desafio.models.response.ProdutoRecomendadoResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.dbc.desafio.mocks.Mocks.mockClientes;
import static com.dbc.desafio.mocks.Mocks.mockComprasResponsesOrdenada;
import static com.dbc.desafio.mocks.Mocks.mockComprasResponsesOrdenadaMaiorCompra;
import static com.dbc.desafio.mocks.Mocks.mockMaiorCompraAno;
import static com.dbc.desafio.mocks.Mocks.mockProdutoResponseVinhoRose;
import static com.dbc.desafio.mocks.Mocks.mockProdutos;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ComprasHelperTest {

    private final ComprasHelper comprasHelper = new ComprasHelper();

    @Test
    void deveConstruirRespostaComprasOrdenadaRetornaListaOrdenada() {
        final List<Produto> produtos = mockProdutos();
        final List<Cliente> clientes = mockClientes();
        final List<CompraResponse> expectedResponse = mockComprasResponsesOrdenada();
        final List<CompraResponse> result = comprasHelper.construirRespostaComprasOrdenada(produtos, clientes);
        assertThat(result).hasSize(4);
        assertEquals(expectedResponse, result);
    }

    @Test
    void deveConstruirRespostaMaiorCompraAnoRetornaListaFiltradaPorAno() {
        final List<Produto> produtos = mockProdutos();
        final List<Cliente> clientes = mockClientes();
        final List<CompraResponse> expected = mockComprasResponsesOrdenadaMaiorCompra();
        final List<CompraResponse> result = comprasHelper.construirRespostaMaiorCompraAno(produtos, clientes, 2022);
        assertThat(result).hasSize(4);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void deveFiltrarMaiorCompraAnoResponseMaiorCompra() {
        final List<CompraResponse> compraResponses = mockComprasResponsesOrdenada();
        final CompraResponse expected = mockMaiorCompraAno();
        final CompraResponse result = comprasHelper.filtrarMaiorCompraAno(compraResponses);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void deveRetornarProdutoRecomendado() {
        final CompraResponse compraResponse = mockMaiorCompraAno();
        final ProdutoRecomendadoResponse expected = mockProdutoResponseVinhoRose();
        final ProdutoRecomendadoResponse result = comprasHelper.obterProdutoRecomendadoResponse(compraResponse);
        assertThat(result.getProduto()).isEqualTo(expected.getProduto());
    }

}