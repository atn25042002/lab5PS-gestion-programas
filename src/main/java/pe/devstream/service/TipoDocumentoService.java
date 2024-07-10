package pe.devstream.service;

import pe.devstream.controller.response.TipoDocumentoResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TipoDocumentoService {
  Mono<List<TipoDocumentoResponse>> listarTipoDocumento();
}
