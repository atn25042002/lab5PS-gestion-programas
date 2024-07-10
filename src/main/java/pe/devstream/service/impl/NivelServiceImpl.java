package pe.devstream.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.devstream.controller.response.NivelReponse;
import pe.devstream.repository.NivelRepository;
import pe.devstream.repository.model.Nivel;
import pe.devstream.service.NivelService;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NivelServiceImpl implements NivelService {
  private final NivelRepository nivelRepository;
  
  @Override
  public Mono<List<NivelReponse>> listarNivel() {
    return nivelRepository.findAll()
      .map(this::convertToNivelResponse)
      .collectList();
  }
  
  private NivelReponse convertToNivelResponse(Nivel genero) {
    return NivelReponse.builder()
      .id(genero.getId())
      .descripcion(genero.getDescripcion())
      .build();
  }
}
