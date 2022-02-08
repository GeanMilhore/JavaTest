package com.vagadevjunior.sigabem.precoFrete.mapper;

import com.vagadevjunior.sigabem.precoFrete.repository.model.LogConsulta;
import com.vagadevjunior.sigabem.precoFrete.responses.controller.CalculoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class LogConsultaMapper {
    public static final LogConsultaMapper INSTANCE = Mappers.getMapper(LogConsultaMapper.class);

    public abstract LogConsulta toLogConsultaEnvio(CalculoResponse calculoResponse);
}
