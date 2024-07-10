package pe.devstream.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.devstream.controller.response.TipoDocumentoResponse;
import pe.devstream.controller.validation.constantes.Constantes;
import pe.devstream.controller.wrapper.WrapperGenericoLista;
import pe.devstream.service.TipoDocumentoService;
import pe.devstream.utils.TipoDocumentoTestUtils;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(TipoDocumentoController.class)
@Import({TipoDocumentoService.class})
class TipoDocumentoControllerTest {
  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private TipoDocumentoService tipoDocumentoService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testListarTipoDocumento() throws JsonProcessingException {
    List<TipoDocumentoResponse> tipoDocumentoResponses = Arrays.asList(TipoDocumentoTestUtils.getTipoDocumentoResponse(), TipoDocumentoTestUtils.getTipoDocumentoResponse());
    WrapperGenericoLista<TipoDocumentoResponse> wrapper = new WrapperGenericoLista<>(tipoDocumentoResponses);
    when(tipoDocumentoService.listarTipoDocumento()).thenReturn(Mono.just(wrapper.getDatos()));

    webTestClient.get()
      .uri("/listar-tipoDocumento")
      .header(Constantes.TRANSACTION_ID, Constantes.TRANSACTION_ID_EXAMPLE)
      .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
      .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
      .exchange()
      .expectStatus().isOk()
      .expectBody()
      .json(objectMapper.writeValueAsString(wrapper));
  }
}