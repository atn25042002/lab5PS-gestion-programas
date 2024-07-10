package pe.devstream.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.devstream.controller.response.NivelReponse;
import pe.devstream.repository.NivelRepository;
import pe.devstream.repository.model.Nivel;
import pe.devstream.service.impl.NivelServiceImpl;
import pe.devstream.utils.NivelTestUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class NivelServiceTest {
  @InjectMocks
  private NivelServiceImpl nivelService;
  @Mock
  private  NivelRepository nivelRepository;
  @Test
   void test_findAll_shouldReturnListOfNivelReponse() {
    List<Nivel> niveles = Arrays.asList(
      NivelTestUtils.getNivel(),
      NivelTestUtils.getNivel()
    );
    List<NivelReponse> expectedNivelesResponse = Arrays.asList(
      NivelTestUtils.getNivelReponse(),
      NivelTestUtils.getNivelReponse()
    );

    when(nivelRepository.findAll()).thenReturn(Flux.fromIterable(niveles));

    StepVerifier.create(nivelService.listarNivel())
      .expectNext(expectedNivelesResponse)
      .verifyComplete();

  }
}
