package com.vagadevjunior.sigabem.precoFrete.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParametroDesconto {
    private Double valor;
    private LocalDate entrega;
}