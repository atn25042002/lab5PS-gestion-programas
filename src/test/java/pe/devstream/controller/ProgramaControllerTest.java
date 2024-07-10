package pe.devstream.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.devstream.controller.request.ProgramaRequest;
import pe.devstream.controller.response.NivelReponse;
import pe.devstream.controller.validation.constantes.Constantes;
import pe.devstream.controller.wrapper.WrapperGenericoLista;
import pe.devstream.controller.wrapper.WrapperGenericoObjetos;
import pe.devstream.service.NivelService;
import pe.devstream.service.ProgramaService;
import pe.devstream.utils.NivelTestUtils;
import pe.devstream.utils.ProgramaTestUtils;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(ProgramaController.class)
@Import({ProgramaService.class})
class ProgramaControllerTest {
  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private ProgramaService programaService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testCrearPrograma() {
    WrapperGenericoObjetos<ProgramaRequest> requestWrapper = new WrapperGenericoObjetos<>(ProgramaTestUtils.getProgramaRequest());
    WrapperGenericoObjetos<Object> wrapper = new WrapperGenericoObjetos<>("Programa creado exitosamente");
    when(programaService.crearPrograma(requestWrapper.getDatos()))
      .thenReturn(Mono.just(wrapper.getDatos()));

    webTestClient.post()
      .uri("/crear-programa")
      .header(Constantes.TRANSACTION_ID, Constantes.TRANSACTION_ID_EXAMPLE)
      .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
      .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(requestWrapper)
      .exchange()
      .expectStatus().isCreated();
  }
}
