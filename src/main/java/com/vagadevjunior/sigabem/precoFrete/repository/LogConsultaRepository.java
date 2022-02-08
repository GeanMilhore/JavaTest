package com.vagadevjunior.sigabem.precoFrete.repository;

import com.vagadevjunior.sigabem.precoFrete.repository.model.LogConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogConsultaRepository extends JpaRepository<LogConsulta, Long> {
}
