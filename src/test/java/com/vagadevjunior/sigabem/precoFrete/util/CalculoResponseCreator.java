package com.vagadevjunior.sigabem.precoFrete.util;

import com.vagadevjunior.sigabem.precoFrete.responses.controller.CalculoResponse;

import java.time.LocalDate;

public class CalculoResponseCreator {

    public static CalculoResponse createValidCalculoResponseBody(){
        return CalculoResponse
                .builder()
                .cepDestino("08474-340")
                .cepOrigem("08532-120")
                .dataPrevistaEntrega(LocalDate.now().plusDays(1))
                .vlTotalFrete(30d)
                .build();
    }
}
