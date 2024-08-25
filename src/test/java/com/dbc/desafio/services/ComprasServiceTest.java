package com.dbc.desafio.services;

import com.dbc.desafio.client.ComprasClientImpl;
import com.dbc.desafio.helpers.ComprasHelper;
import com.dbc.desafio.models.response.CompraResponse;
import com.dbc.desafio.models.response.RecomendacaoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.dbc.desafio.mocks.Mocks.mockClientes;
import static com.dbc.desafio.mocks.Mocks.mockComprasResponsesOrdenada;
import static com.dbc.desafio.mocks.Mocks.mockMaiorCompraAno;
import static com.dbc.desafio.mocks.Mocks.mockProdutos;
import static com.dbc.desafio.mocks.Mocks.mockRecomendacaoResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComprasServiceTest {

    @InjectMocks
    private ComprasService comprasService;

    @Mock
    private ComprasHelper comprasHelper;

    @Mock
    private ComprasClientImpl comprasClient;

    @BeforeEach
    void setUp() {
        when(comprasClient.fetchProdutos()).thenReturn(mockProdutos());
        when(comprasClient.fetchClientesECompras()).thenReturn(mockClientes());
    }

    @Test
    void testDeveRetornarListaDeComprasOrdenada() {
        final List<CompraResponse> expectedResponse = mockComprasResponsesOrdenada();
        when(comprasHelper.construirRespostaComprasOrdenada(mockProdutos(), mockClientes())).thenReturn(expectedResponse);
        final List<CompraResponse> result = comprasService.obterListaComprasOrdenada();
        assertEquals(expectedResponse, result);
    }

    @Test
    void testDeveRetornarMaiorCompraDoAnoQuandoParametroEPassado() {
        final List<CompraResponse> compraResponses = mockComprasResponsesOrdenada();
        final CompraResponse expectedResponse = mockMaiorCompraAno();
        when(comprasHelper.construirRespostaMaiorCompraAno(mockProdutos(), mockClientes(), 2021)).thenReturn(compraResponses);
        when(comprasHelper.filtrarMaiorCompraAno(compraResponses)).thenReturn(expectedResponse);

        final CompraResponse result = comprasService.obterMaiorCompraAno(2021);
        assertEquals(expectedResponse, result);
    }

    @Test
    void testDeveRetornarTopTresClientesFieis() {
        final List<CompraResponse> compraResponses = mockComprasResponsesOrdenada();
        when(comprasHelper.construirRespostaComprasOrdenada(mockProdutos(), mockClientes())).thenReturn(compraResponses);
        final List<CompraResponse> result = comprasService.obterClientesFieis();
        assertThat(result).hasSize(3);
    }

    @Test
    void testDeveRetornarRecomendacoesBaseadoNasComprasMaisFrequentes() {
        final List<CompraResponse> compraResponses = mockComprasResponsesOrdenada();
        final List<RecomendacaoResponse> expectedResponse = mockRecomendacaoResponse();
        when(comprasHelper.construirRespostaComprasOrdenada(mockProdutos(), mockClientes())).thenReturn(compraResponses);
        when(comprasHelper.obterProdutoRecomendadoResponse(compraResponses.get(0))).thenReturn(expectedResponse.get(0).getRecomendacao());
        when(comprasHelper.obterProdutoRecomendadoResponse(compraResponses.get(1))).thenReturn(expectedResponse.get(1).getRecomendacao());
        when(comprasHelper.obterProdutoRecomendadoResponse(compraResponses.get(2))).thenReturn(expectedResponse.get(2).getRecomendacao());
        when(comprasHelper.obterProdutoRecomendadoResponse(compraResponses.get(3))).thenReturn(expectedResponse.get(3).getRecomendacao());

        final List<RecomendacaoResponse> result = comprasService.obterRecomendacao();
        assertThat(result).hasSize(4);
        assertEquals(expectedResponse.get(0), result.get(0));
        assertEquals(expectedResponse.get(1), result.get(1));
        assertEquals(expectedResponse.get(2), result.get(2));
        assertEquals(expectedResponse.get(3), result.get(3));
    }

}