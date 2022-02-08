package com.vagadevjunior.sigabem.precoFrete.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CalculoRequest {
    @NotNull(message = "O Peso não pode ser nulo")
    @Schema(description = "O peso do pacote a ser calculado no frete", example = "50")
    private Double peso;
    @NotEmpty(message = "O CEP origem não pode ser nulo")
    @Schema(description = "O cep de onde o envio será feito", example = "08532120")
    private String cepOrigem;
    @NotEmpty(message = "O CEP destino não pode ser nulo")
    @Schema(description = "O cep de onde o envio será entregue", example = "08474340")
    private String cepDestino;
    @NotEmpty(message = "O Nome do destinatario não pode ser nulo")
    @Schema(description = "O nome de quem está enviando o pacote", example = "Gean Ferreira Milhore")
    private String nomeDestinatario;
}
