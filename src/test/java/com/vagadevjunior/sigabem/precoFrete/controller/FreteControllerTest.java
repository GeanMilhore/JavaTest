package com.vagadevjunior.sigabem.precoFrete.controller;

import com.vagadevjunior.sigabem.precoFrete.requests.CalculoRequest;
import com.vagadevjunior.sigabem.precoFrete.responses.controller.CalculoResponse;
import com.vagadevjunior.sigabem.precoFrete.service.FreteService;
import com.vagadevjunior.sigabem.precoFrete.util.CalculoPostCreator;
import com.vagadevjunior.sigabem.precoFrete.util.CalculoResponseCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
public class FreteControllerTest {

    @InjectMocks
    private FreteController freteController;

    @Mock
    private FreteService freteServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(freteServiceMock.calcular(ArgumentMatchers.any(CalculoRequest.class)))
                .thenReturn(CalculoResponseCreator.createValidCalculoResponseBody());
    }

    @Test
    @DisplayName("calcula frete returns calculoResponse when Successful")
    void calculaFrete_CalculoResponse_WhenSuccessful() {
        Assertions.assertThatCode(() ->
                freteController.calculaFrete(CalculoPostCreator.createCalculoPostRequestBody())
        ).doesNotThrowAnyException();

        ResponseEntity<CalculoResponse> responseEntity =
                freteController.calculaFrete(CalculoPostCreator.createCalculoPostRequestBody());

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
