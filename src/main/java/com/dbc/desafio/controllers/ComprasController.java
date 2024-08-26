package com.dbc.desafio.controllers;

import com.dbc.desafio.models.response.CompraResponse;
import com.dbc.desafio.services.ComprasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class ComprasController {

    final private ComprasService comprasService;

    @Operation(summary = "Retorna uma lista das compras ordenadas por valor, com nome do cliente, CPF, " +
            "nome do produto, quantidade e valor total.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok. (Resposta disponibilizada)."),
            @ApiResponse(responseCode = "400", content = @Content,
                    description = "Bad Request. (Provavelmente um ou mais campos não foram informados)."),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Proibido.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content)})
    @GetMapping(path = "/compras", produces = APPLICATION_JSON_VALUE)
    public List<CompraResponse> obterListaComprasOrdenada() {
     return comprasService.obterListaComprasOrdenada();
    }

    @Operation(summary = "Retorna a maior compra de um cliente em um determinado ano.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok. (Resposta disponibilizada)."),
            @ApiResponse(responseCode = "400", content = @Content,
                    description = "Bad Request. (Provavelmente um ou mais campos não foram informados)."),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Proibido.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content)})
    @GetMapping(path = "/maior-compra/{ano}", produces = APPLICATION_JSON_VALUE)
    public CompraResponse obterMaiorCompraAno(@PathVariable final Integer ano) {
        return comprasService.obterMaiorCompraAno(ano);
    }



}
