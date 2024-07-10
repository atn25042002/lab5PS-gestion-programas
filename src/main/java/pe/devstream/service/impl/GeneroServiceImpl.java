package pe.devstream.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.devstream.controller.response.GeneroResponse;
import pe.devstream.repository.GeneroRepository;
import pe.devstream.repository.model.Genero;
import pe.devstream.service.GeneroService;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {
  private final GeneroRepository generoRepository;
  @Override
  public Mono<List<GeneroResponse>> listarGenero() {
    return generoRepository.findAll()
      .map(this::convertToGeneroDto)
      .collectList();
  }
  private GeneroResponse convertToGeneroDto(Genero genero) {
    return GeneroResponse.builder()
      .id(genero.getId())
      .descripcion(genero.getDescripcion())
      .build();
  }
}
