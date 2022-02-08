package com.vagadevjunior.sigabem.precoFrete.util;

import com.vagadevjunior.sigabem.precoFrete.requests.CalculoRequest;

public class CalculoPostCreator {

    public static CalculoRequest createCalculoPostRequestBody(){
        return CalculoRequest
                .builder()
                .cepDestino("08474340")
                .cepOrigem("08532120")
                .peso(60d)
                .nomeDestinatario("Nome Teste")
                .build();
    }
}
