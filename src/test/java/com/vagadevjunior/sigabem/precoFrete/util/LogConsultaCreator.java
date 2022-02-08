package com.vagadevjunior.sigabem.precoFrete.util;

import com.vagadevjunior.sigabem.precoFrete.repository.model.LogConsulta;

import java.time.LocalDate;

public class LogConsultaCreator {


    public static LogConsulta createLogConsultaToBeSaved(){
        return LogConsulta
                .builder()
                .peso(80d)
                .cepDestino("08532120")
                .cepOrigem("08474340")
                .nomeDestinatario("Nome de Teste")
                .dataConsulta(LocalDate.now())
                .dataPrevistaEntrega(LocalDate.now().plusDays(1))
                .vlTotalFrete(40d)
                .build();
    }

    public static LogConsulta createLogConsultaThatExists(){
        return LogConsulta
                .builder()
                .id(1L)
                .peso(80d)
                .cepDestino("08532120")
                .cepOrigem("08474340")
                .nomeDestinatario("Nome de Teste")
                .dataConsulta(LocalDate.now())
                .dataPrevistaEntrega(LocalDate.now().plusDays(1))
                .vlTotalFrete(40d)
                .build();
    }
}
