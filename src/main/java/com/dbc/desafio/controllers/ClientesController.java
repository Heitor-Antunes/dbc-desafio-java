package com.dbc.desafio.controllers;

import com.dbc.desafio.models.response.CompraResponse;
import com.dbc.desafio.models.response.RecomendacaoResponse;
import com.dbc.desafio.services.ComprasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class ClientesController {

    private final ComprasService comprasService;

    @Operation(summary = "Retorna uma lista de dos 3 clientes com mais compras e o valor total de suas compras.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok. (Resposta disponibilizada)."),
            @ApiResponse(responseCode = "400", content = @Content,
                    description = "Bad Request. (Provavelmente um ou mais campos não foram informados)."),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Proibido.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content)})
    @GetMapping(path = "/clientes-fieis", produces = APPLICATION_JSON_VALUE)
    public List<CompraResponse> obterClientesFieis() {
        return comprasService.obterClientesFieis();
    }

    @Operation(summary = " Retorna uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok. (Resposta disponibilizada)."),
            @ApiResponse(responseCode = "400", content = @Content,
                    description = "Bad Request. (Provavelmente um ou mais campos não foram informados)."),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Proibido.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content)})
    @GetMapping(path = "/recomendacao/cliente/tipo", produces = APPLICATION_JSON_VALUE)
    public List<RecomendacaoResponse> obterRecomendacoes() {
        return comprasService.obterRecomendacao();
    }

}
