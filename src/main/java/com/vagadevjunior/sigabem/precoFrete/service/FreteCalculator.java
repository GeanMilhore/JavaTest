package com.vagadevjunior.sigabem.precoFrete.service;

import com.vagadevjunior.sigabem.precoFrete.responses.viacep.ViaCepResponse;
import com.vagadevjunior.sigabem.precoFrete.service.dto.ParametroDesconto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FreteCalculator {

    @Value("${frete.parametros-desconto.ddd.desconto}")
    private Double DESCONTO_DDD_VALOR;

    @Value("${frete.parametros-desconto.ddd.dias}")
    private Integer DESCONTO_DDD_DIAS;

    @Value("${frete.parametros-desconto.uf.desconto}")
    private Double DESCONTO_UF_VALOR;

    @Value("${frete.parametros-desconto.uf.dias}")
    private Integer DESCONTO_UF_DIAS;

    public ParametroDesconto calcularDesconto(ViaCepResponse origem, ViaCepResponse destino) {
        if (destino.getDdd().equals(origem.getDdd())) {
            return ParametroDesconto.builder()
                    .valor(DESCONTO_DDD_VALOR)
                    .entrega(calcularDias(DESCONTO_DDD_DIAS))
                    .build();
        }

        if (destino.getUf().equals(origem.getUf())) {
            return ParametroDesconto.builder()
                    .valor(DESCONTO_UF_VALOR)
                    .entrega(calcularDias(DESCONTO_UF_DIAS))
                    .build();
        }

        return new ParametroDesconto(0d, calcularDias(10));
    }

    private LocalDate calcularDias(Integer dias) {
        return LocalDate.now().plusDays(dias);
    }

    public double efetuarCalculoFinal(Double peso, ParametroDesconto parametroDesconto) {
        return peso - (peso * parametroDesconto.getValor());
    }
}
