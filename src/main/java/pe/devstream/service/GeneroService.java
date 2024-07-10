package pe.devstream.service;

import pe.devstream.controller.response.GeneroResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GeneroService {
  Mono<List<GeneroResponse>> listarGenero();
}
