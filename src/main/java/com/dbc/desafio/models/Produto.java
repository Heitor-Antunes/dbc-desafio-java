package com.dbc.desafio.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    
    private Integer codigo;
    
    @JsonAlias(value = "tipo_vinho")
    private String tipoVinho;

    private BigDecimal preco;

    private String safra;

    @JsonAlias(value = "ano_compra")
    private Integer anoCompra;
}
