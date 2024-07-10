package pe.devstream.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.devstream.controller.response.NivelReponse;
import pe.devstream.controller.response.ParametrosReponse;
import pe.devstream.repository.NivelRepository;
import pe.devstream.repository.ParametrosRepository;
import pe.devstream.repository.model.Nivel;
import pe.devstream.repository.model.Parametros;
import pe.devstream.service.NivelService;
import pe.devstream.service.ParametrosService;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ParametrosServiceImpl implements ParametrosService {
  private final ParametrosRepository parametrosRepository;
  @Override
  public Mono<List<ParametrosReponse>> listarParametros() {
    return parametrosRepository.findAll()
      .map(this::convertToGeneroDto)
      .collectList();
  }
  private ParametrosReponse convertToGeneroDto(Parametros parametro) {
    return ParametrosReponse.builder()
      .id(parametro.getId())
      .nombre(parametro.getNombre())
      .build();
  }
}

