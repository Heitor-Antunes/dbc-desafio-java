package com.dbc.desafio.services;

import com.dbc.desafio.client.ComprasClient;
import com.dbc.desafio.helpers.ComprasHelper;
import com.dbc.desafio.models.Cliente;
import com.dbc.desafio.models.Produto;
import com.dbc.desafio.models.response.CompraResponse;
import com.dbc.desafio.models.response.RecomendacaoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ComprasService {

    private final ComprasHelper comprasHelper;
    private final ComprasClient comprasClient;

    public List<CompraResponse> obterListaComprasOrdenada() {
        final List<Produto> produtos = comprasClient.fetchProdutos();
        final List<Cliente> clientes = comprasClient.fetchClientesECompras();
        return comprasHelper.construirRespostaComprasOrdenada(produtos, clientes);
    }

    public CompraResponse obterMaiorCompraAno(final Integer ano) {
        final List<Produto> produtos = comprasClient.fetchProdutos();
        final List<Cliente> clientes = comprasClient.fetchClientesECompras();
        return comprasHelper.filtrarMaiorCompraAno(
                comprasHelper.construirRespostaMaiorCompraAno(produtos, clientes, ano)
        );
    }

    public List<CompraResponse> obterClientesFieis() {
        final List<Produto> produtos = comprasClient.fetchProdutos();
        final List<Cliente> clientes = comprasClient.fetchClientesECompras();
        return comprasHelper.construirRespostaComprasOrdenada(produtos, clientes).stream()
                .sorted((c1, c2) -> Double.compare(
                        c2.getCompras().stream().mapToDouble(compra -> compra.getValorTotal().doubleValue()).sum(),
                        c1.getCompras().stream().mapToDouble(compra -> compra.getValorTotal().doubleValue()).sum()))
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<RecomendacaoResponse> obterRecomendacao() {
        final List<Produto> produtos = comprasClient.fetchProdutos();
        final List<Cliente> clientes = comprasClient.fetchClientesECompras();
        return comprasHelper.construirRespostaComprasOrdenada(produtos, clientes).stream()
                .map(compraResponse -> RecomendacaoResponse.builder()
                        .nome(compraResponse.getNome())
                        .cpf(compraResponse.getCpf())
                        .recomendacao(comprasHelper.obterProdutoRecomendadoResponse(compraResponse))
                        .build())
                .collect(Collectors.toList());
    }
}