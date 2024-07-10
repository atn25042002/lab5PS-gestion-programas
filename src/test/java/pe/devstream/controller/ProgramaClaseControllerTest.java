package pe.devstream.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.devstream.controller.response.ProgramaClaseResponse;
import pe.devstream.controller.validation.constantes.Constantes;
import pe.devstream.controller.wrapper.WrapperGenericoLista;
import pe.devstream.service.ProgramaClaseService;
import pe.devstream.utils.ProgramaClaseTestUtils;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@WebFluxTest(ProgramaClaseController.class)
@Import({ProgramaClaseService.class})
public class ProgramaClaseControllerTest  {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProgramaClaseService programaClaseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarProgramaClase() throws JsonProcessingException{
        List<ProgramaClaseResponse> programaClaseResponses = Arrays.asList(ProgramaClaseTestUtils.getProgramaClaseResponse());
        WrapperGenericoLista<ProgramaClaseResponse> wrapper = new WrapperGenericoLista<>(programaClaseResponses);
        when(programaClaseService.listarProgramasClase()).thenReturn(Mono.just(wrapper.getDatos()));

        webTestClient.get()
        .uri("/listar-programa-clase")
        .header(Constantes.TRANSACTION_ID, Constantes.TRANSACTION_ID_EXAMPLE)
        .header(Constantes.APPLICATION_NAME, Constantes.APPLICATION_NAME_EXAMPLE)
        .header(Constantes.USER_CONSUMER_ID, Constantes.USER_CONSUMER_ID_EXAMPLE)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .json(objectMapper.writeValueAsString(wrapper));
    }

    @Test
    void testListarProgramaClase2() throws JsonProcessingException{
        
    }
}
