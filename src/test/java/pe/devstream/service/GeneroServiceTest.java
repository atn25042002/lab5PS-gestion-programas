package pe.devstream.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.devstream.controller.response.GeneroResponse;
import pe.devstream.repository.GeneroRepository;
import pe.devstream.repository.model.Genero;
import pe.devstream.service.impl.GeneroServiceImpl;
import pe.devstream.utils.GeneroTestUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeneroServiceTest {

  @InjectMocks
  private GeneroServiceImpl generoService;
  @Mock
  private GeneroRepository generoRepository;

  @Test
  void test_should_return_mono_list_genero_response() {
    List<Genero> generos = Arrays.asList(GeneroTestUtils.getGenero(),GeneroTestUtils.getGenero());
    List<GeneroResponse> expectedGeneroResponses = Arrays.asList(GeneroTestUtils.getGeneroResponse(), GeneroTestUtils.getGeneroResponse());

    when(generoRepository.findAll()).thenReturn(Flux.fromIterable(generos));

    StepVerifier.create(generoService.listarGenero())
            .expectNext(expectedGeneroResponses)
            .verifyComplete();
  }
}
