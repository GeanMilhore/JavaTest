package com.vagadevjunior.sigabem.precoFrete.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Frete {
    private LocalDate previsaoEntrega;
    private Double valorEntrega;
}
