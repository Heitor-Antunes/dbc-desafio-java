package com.dbc.desafio.configuration;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.requestFactory(new RequestFactorySupplier()).build();
    }

    static class RequestFactorySupplier implements Supplier<ClientHttpRequestFactory> {

        @Override
        public ClientHttpRequestFactory get() {

            var poolingConnectionManager =
                    new PoolingHttpClientConnectionManager();

            var client =
                    HttpClientBuilder.create().setConnectionManager(poolingConnectionManager).build();

            return new HttpComponentsClientHttpRequestFactory(client);
        }
    }
}
