package pe.devstream.service;

import pe.devstream.controller.response.ParametrosReponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ParametrosService {
  Mono<List<ParametrosReponse>> listarParametros();
}

