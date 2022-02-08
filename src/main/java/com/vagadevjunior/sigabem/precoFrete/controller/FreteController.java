package com.vagadevjunior.sigabem.precoFrete.controller;

import com.vagadevjunior.sigabem.precoFrete.requests.CalculoRequest;
import com.vagadevjunior.sigabem.precoFrete.responses.controller.CalculoResponse;
import com.vagadevjunior.sigabem.precoFrete.service.FreteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/calculafrete")
@AllArgsConstructor
public class FreteController {

    private FreteService freteService;

    @PostMapping
    @Operation( summary = "calcula  frete a partir de dois ceps",
            description = "A partir de dois ceps calcula o preço do frete, de acordo com o ddd e estado")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @ApiResponse(responseCode = "400", description = "If There Are Some Emptys Fields")
    public ResponseEntity<CalculoResponse> calculaFrete(@RequestBody @Valid CalculoRequest dadosEnvio){

        return ResponseEntity.ok().body(freteService.calcular(dadosEnvio));
    }
}
