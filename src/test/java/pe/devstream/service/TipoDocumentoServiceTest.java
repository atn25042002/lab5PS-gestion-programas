package pe.devstream.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.devstream.controller.response.TipoDocumentoResponse;
import pe.devstream.repository.TipoDocumentoRepository;
import pe.devstream.repository.model.TipoDocumento;
import pe.devstream.service.impl.TipoDocumentoServiceImpl;
import pe.devstream.utils.TipoDocumentoTestUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TipoDocumentoServiceTest {
  @InjectMocks
  private TipoDocumentoServiceImpl tipoDocumentoService;
  @Mock
  private TipoDocumentoRepository tipoDocumentoRepository;
  @Test
  void test_findAll_Successful() {

    List<TipoDocumento> tipoDocumentos = Arrays.asList(
      TipoDocumentoTestUtils.getTipoDocumento(), TipoDocumentoTestUtils.getTipoDocumento()
    );
    List<TipoDocumentoResponse> tipoDocumentosReponse = Arrays.asList(
      TipoDocumentoTestUtils.getTipoDocumentoResponse(), TipoDocumentoTestUtils.getTipoDocumentoResponse()
    );
    when(tipoDocumentoRepository.findAll()).thenReturn(Flux.fromIterable(tipoDocumentos));

    StepVerifier.create(tipoDocumentoService.listarTipoDocumento())
      .expectNext(tipoDocumentosReponse)
      .verifyComplete();
  }
}
