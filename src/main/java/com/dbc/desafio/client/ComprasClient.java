package com.dbc.desafio.client;

import com.dbc.desafio.models.Cliente;
import com.dbc.desafio.models.Produto;

import java.util.List;

public interface ComprasClient {

    List<Produto> fetchProdutos();
    List<Cliente> fetchClientesECompras();

}
