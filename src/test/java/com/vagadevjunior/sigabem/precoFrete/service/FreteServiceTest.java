package com.vagadevjunior.sigabem.precoFrete.service;

import com.vagadevjunior.sigabem.precoFrete.repository.LogConsultaRepository;
import com.vagadevjunior.sigabem.precoFrete.repository.model.LogConsulta;
import com.vagadevjunior.sigabem.precoFrete.requests.CalculoRequest;
import com.vagadevjunior.sigabem.precoFrete.responses.controller.CalculoResponse;
import com.vagadevjunior.sigabem.precoFrete.responses.viacep.ViaCepResponse;
import com.vagadevjunior.sigabem.precoFrete.util.CalculoPostCreator;
import com.vagadevjunior.sigabem.precoFrete.util.CalculoResponseCreator;
import com.vagadevjunior.sigabem.precoFrete.util.FreteCreator;
import com.vagadevjunior.sigabem.precoFrete.util.LogConsultaCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class FreteServiceTest {

    @Mock
    private FreteService freteService;

    @Mock
    private LogConsultaRepository logConsultaRepositoryMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(logConsultaRepositoryMock
                        .save(ArgumentMatchers.any(LogConsulta.class)))
                .thenReturn(LogConsultaCreator.createLogConsultaThatExists());

        BDDMockito.when(freteService.calcularFrete(ArgumentMatchers.anyDouble(),
                ArgumentMatchers.any(ViaCepResponse.class),
                ArgumentMatchers.any(ViaCepResponse.class))).thenReturn(
                FreteCreator.createValidFrete(10, 50d)
        );

        BDDMockito.when(freteService.registraConsulta(ArgumentMatchers.any(CalculoResponse.class),
                ArgumentMatchers.any(CalculoRequest.class))).thenReturn(
                LogConsultaCreator.createLogConsultaThatExists()
        );
    }

    @Test
    @DisplayName("save returns LogConsulta when successful")
    void registraConsulta_returnsLogConsulta_whenSuccessful() {
        CalculoResponse calculoResponse = CalculoResponseCreator.createValidCalculoResponseBody();
        CalculoRequest calculoPost = CalculoPostCreator.createCalculoPostRequestBody();

        LogConsulta novaConsulta = freteService.registraConsulta(calculoResponse, calculoPost);

        Assertions.assertThat(novaConsulta).isNotNull().isEqualTo(LogConsultaCreator.createLogConsultaThatExists());
    }
}
