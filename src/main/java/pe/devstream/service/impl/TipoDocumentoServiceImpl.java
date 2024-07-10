package pe.devstream.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.devstream.controller.response.TipoDocumentoResponse;
import pe.devstream.repository.TipoDocumentoRepository;
import pe.devstream.repository.model.TipoDocumento;
import pe.devstream.service.TipoDocumentoService;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
  private final TipoDocumentoRepository tipoDocumentoRepository;
  
  @Override
  public Mono<List<TipoDocumentoResponse>> listarTipoDocumento() {
    return tipoDocumentoRepository.findAll()
      .map(this::convertToTipoDocumentoDto)
      .collectList();
  }
  
  private TipoDocumentoResponse convertToTipoDocumentoDto(TipoDocumento tipoDocumento) {
    return TipoDocumentoResponse.builder()
      .id(tipoDocumento.getId())
      .descripcion(tipoDocumento.getDescripcion())
      .build();
  }
}
