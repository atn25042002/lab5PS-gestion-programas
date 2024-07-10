package pe.devstream.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.FetchSpec;
import pe.devstream.controller.response.EstudianteResponse;
import pe.devstream.repository.impl.EstudianteRepositoryImpl;
import pe.devstream.repository.model.Estudiante;
import pe.devstream.utils.EstudianteTestUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstudianteRepositoryTest {

  @InjectMocks
  private EstudianteRepositoryImpl estudianteRepository;

  @Mock
  private DatabaseClient databaseClient;

  @Mock
  private FetchSpec<Map<String, Object>>  fetchSpec;

  @Mock
  private DatabaseClient.GenericExecuteSpec genericExecuteSpec;

  @Test
  void testGetAllEstudiantesPorPrograma() {
    // Arrange
    Long testId = 1L;
    when(databaseClient.sql(anyString())).thenReturn(genericExecuteSpec);
    when(genericExecuteSpec.bind(anyString(), any())).thenReturn(genericExecuteSpec);
    when(genericExecuteSpec.fetch()).thenReturn(fetchSpec);
    // Define the expected result
    List<Map<String, Object>> expectedResult = EstudianteTestUtils.getEstudiantesSqlHashMap();

    when(fetchSpec.all()).thenReturn(Flux.fromIterable(expectedResult));

    Flux<EstudianteResponse> result = estudianteRepository.getAllEstudiantesPorPrograma(testId);
    // Assert
    StepVerifier.create(result)
      .expectNextCount(3)
      .verifyComplete();
  }
}
