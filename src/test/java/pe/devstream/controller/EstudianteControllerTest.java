package pe.devstream.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.devstream.controller.request.ProgramaEstudiantesRequest;
import pe.devstream.controller.response.EstudianteResponse;
import pe.devstream.controller.validation.constantes.Constantes;
import pe.devstream.controller.wrapper.WrapperGenericoLista;
import pe.devstream.controller.wrapper.WrapperGenericoObjetos;
import pe.devstream.service.EstudianteService;
import pe.devstream.utils.EstudianteTestUtils;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(EstudianteController.class)
@Import({EstudianteService.class})
class EstudianteControllerTest {
  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private EstudianteService estudianteService;

  @Autowired
  private ObjectMapper objectMapper;
  private List<EstudianteResponse> expectedEstudiantes;

  @BeforeEach
  void setUp()  {
    expectedEstudiantes = Arrays.asList(
      EstudianteTestUtils.getEstudianteResponse(), EstudianteTestUtils.getEstudianteResponse()
    );
  }

  @Test
  void testListarEstudianteProgram() throws JsonProcessingException {
    WrapperGenericoLista<EstudianteResponse> wrapper = new WrapperGenericoLista<>(expectedEstudiantes);
    when(estudianteService.listarEstudiantePorPrograma(any(Long.class)))
      .thenReturn(Mono.just(wrapper.getDatos()));

    webTestClient.get()
      .uri("/listar-estudiante-programa/{id}", 1)
      .header(Constantes.TRANSACTION_ID, Constantes.TRANSACTION_ID_EXAMPLE)
      .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
      .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
      .exchange()
      .expectStatus().isOk()
      .expectBody()
      .json(objectMapper.writeValueAsString(wrapper));
  }
  @Test
  void testGuardarEstudiantes() {
    ProgramaEstudiantesRequest programaEstudiantesRequest = EstudianteTestUtils.getProgramaEstudiantesRequest();
    WrapperGenericoObjetos<ProgramaEstudiantesRequest> wrapperRequest = new WrapperGenericoObjetos<>(programaEstudiantesRequest);
    WrapperGenericoObjetos<Object> wrapperResponse = new WrapperGenericoObjetos<>("Programa creado exitosamente");

    when(estudianteService.guardarEstudiantes(wrapperRequest.getDatos()))
      .thenReturn(Mono.just(wrapperResponse.getDatos()));

    webTestClient.post()
      .uri("/crear-estudiante-programa-masivo")
      .header(Constantes.TRANSACTION_ID, Constantes.TRANSACTION_ID_EXAMPLE)
      .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
      .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(wrapperRequest)
      .exchange()
      .expectStatus().isOk();
  }
  @Test
  void testGuardarEstudiantesFailSql() {
    ProgramaEstudiantesRequest programaEstudiantesRequest = EstudianteTestUtils.getProgramaEstudiantesRequest();
    WrapperGenericoObjetos<ProgramaEstudiantesRequest> wrapperRequest = new WrapperGenericoObjetos<>(programaEstudiantesRequest);

    when(estudianteService.guardarEstudiantes(wrapperRequest.getDatos()))
      .thenReturn(Mono.error(new SQLException("No hikari pool")));

    webTestClient.post()
      .uri("/crear-estudiante-programa-masivo")
      .header(Constantes.TRANSACTION_ID, Constantes.TRANSACTION_ID_EXAMPLE)
      .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
      .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(wrapperRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }
  @Test
  void testListarEstudianteProgramUrlNotFound()  {
    WrapperGenericoLista<EstudianteResponse> wrapper = new WrapperGenericoLista<>(expectedEstudiantes);
    when(estudianteService.listarEstudiantePorPrograma(any(Long.class)))
      .thenReturn(Mono.just(wrapper.getDatos()));

    webTestClient.get()
      .uri("/listar-estudiante-programas/{id}", 1)
      .header(Constantes.TRANSACTION_ID, Constantes.TRANSACTION_ID_EXAMPLE)
      .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
      .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
      .exchange()
      .expectStatus().is5xxServerError();
  }
  @Test
  void testListarEstudianteFailTransactionId() {
    WrapperGenericoLista<EstudianteResponse> wrapper = new WrapperGenericoLista<>(expectedEstudiantes);
    when(estudianteService.listarEstudiantePorPrograma(any(Long.class)))
      .thenReturn(Mono.just(wrapper.getDatos()));

    webTestClient.get()
      .uri("/listar-estudiante-programa/{id}", 1)
      .header("Transaccion-Id", "2ba68a48-c7d7-4997-a0e6")
      .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
      .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
      .exchange()
      .expectStatus().isBadRequest();
  }

}