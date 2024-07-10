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
import pe.devstream.controller.response.GeneroResponse;
import pe.devstream.controller.validation.constantes.Constantes;
import pe.devstream.controller.wrapper.WrapperGenericoLista;
import pe.devstream.service.GeneroService;
import pe.devstream.utils.GeneroTestUtils;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(GeneroController.class)
@Import({GeneroService.class})
class GeneroControllerTest {
  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private GeneroService generoService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testListarGenero() throws JsonProcessingException {
    List<GeneroResponse> generoResponses = Arrays.asList(GeneroTestUtils.getGeneroResponse(), GeneroTestUtils.getGeneroResponse());
    WrapperGenericoLista<GeneroResponse> wrapper = new WrapperGenericoLista<>(generoResponses);
    when(generoService.listarGenero()).thenReturn(Mono.just(wrapper.getDatos()));

    webTestClient.get()
      .uri("/listar-genero")
      .header(Constantes.TRANSACTION_ID, Constantes.TRANSACTION_ID_EXAMPLE)
      .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
      .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
      .exchange()
      .expectStatus().isOk()
      .expectBody()
      .json(objectMapper.writeValueAsString(wrapper));
  }
}
