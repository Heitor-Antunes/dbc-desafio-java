package com.dbc.desafio.client;

import com.dbc.desafio.models.Cliente;
import com.dbc.desafio.models.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ComprasClientImpl implements ComprasClient{

    private static final String PRODUTOS_URL_COMPLEMENT = "/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json";
    private static final String CLIENTES_URL_COMPLEMENT = "/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json";

    private final RestTemplate restTemplate;

    @Value("${external.dbc.base-url}")
    private String baseUrl;

    @Override
    public List<Produto> fetchProdutos() {
        return executeGetRequest(PRODUTOS_URL_COMPLEMENT, Produto[].class);
    }

    @Override
    public List<Cliente> fetchClientesECompras() {
        return executeGetRequest(CLIENTES_URL_COMPLEMENT, Cliente[].class);
    }

    private <T> List<T> executeGetRequest(final String urlComplement, final Class<T[]> responseType) {
        try {
            final HttpEntity<Void> entity = new HttpEntity<>(new HttpHeaders());
            final String uriBuilder = UriComponentsBuilder.fromUriString(baseUrl + urlComplement).toUriString();
            final ResponseEntity<T[]> response = restTemplate.exchange(uriBuilder, HttpMethod.GET, entity, responseType);
            return new ArrayList<>(Arrays.asList(Objects.requireNonNull(response.getBody())));
        } catch (Exception e) {
            log.error("Erro ao obter informações para URL: {}", baseUrl + urlComplement, e);
            return new ArrayList<>();
        }
    }
}