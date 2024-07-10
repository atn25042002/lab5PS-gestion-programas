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
import pe.devstream.controller.response.NivelReponse;
import pe.devstream.controller.validation.constantes.Constantes;
import pe.devstream.controller.wrapper.WrapperGenericoLista;
import pe.devstream.service.NivelService;
import pe.devstream.utils.NivelTestUtils;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(NivelController.class)
@Import({NivelService.class})
class NivelControllerTest {
  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private NivelService nivelService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testListarNivel() throws JsonProcessingException {
    List<NivelReponse> nivelReponses = Arrays.asList(NivelTestUtils.getNivelReponse(), NivelTestUtils.getNivelReponse() );
    WrapperGenericoLista<NivelReponse> wrapper = new WrapperGenericoLista<>(nivelReponses);
    when(nivelService.listarNivel()).thenReturn(Mono.just(wrapper.getDatos()));

    webTestClient.get()
      .uri("/listar-nivel")
      .header(Constantes.TRANSACTION_ID, Constantes.TRANSACTION_ID_EXAMPLE)
      .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
      .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
      .exchange()
      .expectStatus().isOk()
      .expectBody()
      .json(objectMapper.writeValueAsString(wrapper));
  }
}
