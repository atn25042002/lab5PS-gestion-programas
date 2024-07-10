package pe.devstream.service;

import pe.devstream.controller.response.NivelReponse;
import reactor.core.publisher.Mono;
import java.util.List;

public interface NivelService  {
  Mono<List<NivelReponse>> listarNivel();
}
