package com.dbc.desafio.client;

import com.dbc.desafio.models.Cliente;
import com.dbc.desafio.models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class ComprasClientImplTest {

    @Autowired
    private ComprasClientImpl comprasClient;

    @MockBean
    private RestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com";
    }

    @Test
    void deveRetornarListaDeProdutos() {
        Produto[] produtos = {new Produto(), new Produto()};
        ResponseEntity<Produto[]> responseEntity = new ResponseEntity<>(produtos, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(baseUrl + "/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Produto[].class)
        )).thenReturn(responseEntity);

        final List<Produto> result = comprasClient.fetchProdutos();
        assertThat(result).hasSize(2);
    }

    @Test
    void deveRetornarListaDeClientesECompras() {
        Cliente[] clientes = {new Cliente(), new Cliente()};
        ResponseEntity<Cliente[]> responseEntity = new ResponseEntity<>(clientes, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(baseUrl + "/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Cliente[].class)
        )).thenReturn(responseEntity);

        final List<Cliente> result = comprasClient.fetchClientesECompras();
        assertThat(result).hasSize(2);
    }

    @Test
    void deveRetornarListaVaziaQuandoErroAoBuscarProdutos() {
        when(restTemplate.exchange(
                eq(baseUrl + "/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Produto[].class)
        )).thenThrow(new RuntimeException("Erro ao buscar produtos"));

        final List<Produto> result = comprasClient.fetchProdutos();
        assertThat(result).isEmpty();
    }

    @Test
    void deveRetornarListaVaziaQuandoErroAoBuscarClientesECompras() {
        when(restTemplate.exchange(
                eq(baseUrl + "/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Cliente[].class)
        )).thenThrow(new RuntimeException("Erro ao buscar clientes e compras"));

        final List<Cliente> result = comprasClient.fetchClientesECompras();
        assertThat(result).isEmpty();
    }
}