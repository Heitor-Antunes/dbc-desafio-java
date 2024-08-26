package com.dbc.desafio.helpers;

import com.dbc.desafio.models.Cliente;
import com.dbc.desafio.models.Compra;
import com.dbc.desafio.models.Produto;
import com.dbc.desafio.models.response.CompraResponse;
import com.dbc.desafio.models.response.ProdutoRecomendadoResponse;
import com.dbc.desafio.models.response.ValorTotalCompraResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ComprasHelper {

    public List<CompraResponse> construirRespostaComprasOrdenada(final List<Produto> produtos,
                                                                 final List<Cliente> clientes) {
        final Map<String, Produto> produtosMap = produtos.stream()
                .collect(Collectors.toMap(produto -> String.valueOf(produto.getCodigo()), produto -> produto));

        return clientes.stream().map(cliente -> CompraResponse.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .compras(construirCompras(cliente, produtosMap))
                .build()).collect(Collectors.toList());
    }

    public List<CompraResponse> construirRespostaMaiorCompraAno(final List<Produto> produtos,
                                                                final List<Cliente> clientes,
                                                                final Integer ano) {
        final Map<String, Produto> produtosMap = produtos.stream()
                .collect(Collectors.toMap(produto -> String.valueOf(produto.getCodigo()), produto -> produto));

        return clientes.stream().map(cliente -> CompraResponse.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .compras(obterMaiorCompraAno(cliente, produtosMap, ano))
                .build()).collect(Collectors.toList());
    }

    public CompraResponse filtrarMaiorCompraAno(final List<CompraResponse> compraResponses) {
        return compraResponses.stream()
                .flatMap(compraResponse -> compraResponse.getCompras().stream()
                        .map(compraMap -> new AbstractMap.SimpleEntry<>(compraResponse, compraMap)))
                .max(Comparator.comparing(compra -> compra.getValue().getValorTotal()))
                .map(compraResponse -> CompraResponse.builder()
                        .nome(compraResponse.getKey().getNome())
                        .cpf(compraResponse.getKey().getCpf())
                        .compras(List.of(compraResponse.getValue()))
                        .build())
                .orElse(null);
    }

    public ProdutoRecomendadoResponse obterProdutoRecomendadoResponse(final CompraResponse compraResponse) {
        return compraResponse.getCompras()
                .stream()
                .max(Comparator.comparing(ValorTotalCompraResponse::getQuantidade))
                .map(valorTotalCompraResponse -> ProdutoRecomendadoResponse.builder()
                        .produto(valorTotalCompraResponse.getProduto())
                        .build())
                .orElse(null);
    }

    private List<ValorTotalCompraResponse> obterMaiorCompraAno(final Cliente cliente,
                                                               final Map<String, Produto> produtosMap,
                                                               final Integer ano) {
        return cliente.getCompras().stream().map(compra -> ValorTotalCompraResponse.builder()
                        .produto(produtosMap.get(compra.getCodigo()))
                        .quantidade(compra.getQuantidade())
                        .valorTotal(calcularValorTotalCompra(compra, produtosMap))
                        .build())
                .filter(compra -> compra.getProduto().getAnoCompra().equals(ano))
                .collect(Collectors.toList());
    }

    private List<ValorTotalCompraResponse> construirCompras(final Cliente cliente,
                                                            final Map<String, Produto> produtosMap) {
        return cliente.getCompras().stream().map(compra -> ValorTotalCompraResponse.builder()
                        .produto(produtosMap.get(compra.getCodigo()))
                        .quantidade(compra.getQuantidade())
                        .valorTotal(calcularValorTotalCompra(compra, produtosMap))
                        .build())
                .sorted(Comparator.comparing(ValorTotalCompraResponse::getValorTotal))
                .collect(Collectors.toList());
    }

    private BigDecimal calcularValorTotalCompra(final Compra compra, final Map<String, Produto> produtosMap) {
        final Produto produto = produtosMap.get(compra.getCodigo());
        if (produto == null) {
            log.warn("Produto com código {} não encontrado", compra.getCodigo());
            return BigDecimal.ZERO;
        }
        return produto.getPreco().multiply(BigDecimal.valueOf(compra.getQuantidade()));
    }

}