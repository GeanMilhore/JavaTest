package com.vagadevjunior.sigabem.precoFrete.repository;

import com.vagadevjunior.sigabem.precoFrete.repository.model.LogConsulta;
import com.vagadevjunior.sigabem.precoFrete.util.LogConsultaCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

@Log4j2
@DataJpaTest
@DisplayName("Tests for LogConsulta Repository ")
public class LogConsultaRepositoryTest {
    @Autowired
    private LogConsultaRepository logConsultaRepository;

    @DisplayName("Save creates consulta data when successful")
    @Test
    void save_PersistConsultaData_whenSuccessful(){
        LogConsulta dadosToBeSaved = LogConsultaCreator.createLogConsultaToBeSaved();

        LogConsulta savedDados = this.logConsultaRepository.save(dadosToBeSaved);

        Assertions.assertThat(savedDados).isNotNull();
        Assertions.assertThat(savedDados.getId()).isNotNull();
        Assertions.assertThat(savedDados.getVlTotalFrete()).isEqualTo(dadosToBeSaved.getVlTotalFrete());
    }

    @DisplayName("Save Throws ConstraintViolationException when there's some field empty")
    @Test
    void save_ThrowConstraintViolationException_whenThereAreFieldsEmpty(){
        LogConsulta dadosToBeSaved = LogConsultaCreator.createLogConsultaToBeSaved();
        dadosToBeSaved.setDataConsulta(null);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.logConsultaRepository.save(dadosToBeSaved))
                .withMessageContaining("A data da consulta não pode ser nula");
    }

}
