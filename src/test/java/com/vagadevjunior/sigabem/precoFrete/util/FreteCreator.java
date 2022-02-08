package com.vagadevjunior.sigabem.precoFrete.util;

import com.vagadevjunior.sigabem.precoFrete.service.dto.Frete;

import java.time.LocalDate;

public class FreteCreator {

    public static Frete createValidFrete(Integer days, Double valorEntrega) {
        return Frete
                .builder()
                .previsaoEntrega(LocalDate.now().plusDays(days))
                .valorEntrega(valorEntrega)
                .build();
    }
}
