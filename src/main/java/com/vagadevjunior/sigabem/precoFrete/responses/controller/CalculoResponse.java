package com.vagadevjunior.sigabem.precoFrete.responses.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class CalculoResponse {
    private Double vlTotalFrete;
    private LocalDate dataPrevistaEntrega;
    private String cepOrigem;
    private String cepDestino;
}
