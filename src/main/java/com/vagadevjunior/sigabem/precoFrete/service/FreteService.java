package com.vagadevjunior.sigabem.precoFrete.service;

import com.vagadevjunior.sigabem.precoFrete.mapper.LogConsultaMapper;
import com.vagadevjunior.sigabem.precoFrete.repository.LogConsultaRepository;
import com.vagadevjunior.sigabem.precoFrete.repository.model.LogConsulta;
import com.vagadevjunior.sigabem.precoFrete.requests.CalculoRequest;
import com.vagadevjunior.sigabem.precoFrete.responses.controller.CalculoResponse;
import com.vagadevjunior.sigabem.precoFrete.responses.viacep.ViaCepResponse;
import com.vagadevjunior.sigabem.precoFrete.service.dto.Frete;
import com.vagadevjunior.sigabem.precoFrete.service.dto.ParametroDesconto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class FreteService {

    private FreteCalculator freteCalculator;

    private LogConsultaRepository logConsultaRepository;

    private LogConsultaMapper logConsultaMapper;

    public ViaCepResponse pegaDadosCEP(String cep) {
        ResponseEntity<ViaCepResponse> exchange = new RestTemplate().exchange(
                "https://viacep.com.br/ws/" + cep + "/json/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return exchange.getBody();
    }

    public Frete calcularFrete(Double peso, ViaCepResponse origem, ViaCepResponse destino) {

        ParametroDesconto parametroDesconto = freteCalculator.calcularDesconto(origem, destino);
        Double valorTotal = freteCalculator.efetuarCalculoFinal(peso, parametroDesconto);
        return Frete
                .builder()
                .previsaoEntrega(parametroDesconto.getEntrega())
                .valorEntrega(valorTotal)
                .build();
    }

    public LogConsulta registraConsulta(CalculoResponse calculoResponse, CalculoRequest dadosEnvio) {
        LogConsulta logConsulta = logConsultaMapper.toLogConsultaEnvio(calculoResponse);
        logConsulta.setDataConsulta(LocalDate.now());
        logConsulta.setNomeDestinatario(dadosEnvio.getNomeDestinatario());
        logConsulta.setPeso(dadosEnvio.getPeso());
        return logConsultaRepository.save(logConsulta);
    }

    public CalculoResponse calcular(CalculoRequest dadosEnvio) {

        ViaCepResponse dadosOrigem = pegaDadosCEP(dadosEnvio.getCepOrigem());
        ViaCepResponse dadosDestino = pegaDadosCEP(dadosEnvio.getCepDestino());

        Frete frete =  calcularFrete(dadosEnvio.getPeso(), dadosOrigem, dadosDestino);

        CalculoResponse calculoResponse = CalculoResponse.builder()
                .cepDestino(dadosDestino.getCep())
                .cepOrigem(dadosOrigem.getCep())
                .dataPrevistaEntrega(frete.getPrevisaoEntrega())
                .vlTotalFrete(frete.getValorEntrega())
                .build();

        registraConsulta(calculoResponse, dadosEnvio);
        return calculoResponse;
    }
}
