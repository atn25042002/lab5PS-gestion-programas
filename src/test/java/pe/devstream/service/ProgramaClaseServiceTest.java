package pe.devstream.service;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import pe.devstream.controller.response.ProgramaClaseResponse;
import pe.devstream.repository.ProgramaClaseRepository;
import pe.devstream.repository.model.ProgramaClase;
import pe.devstream.service.impl.ProgramaClaseServiceImpl;
import pe.devstream.utils.ProgramaClaseTestUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ProgramaClaseServiceTest {

    @InjectMocks
    private ProgramaClaseServiceImpl programaClaseService;

    @Mock
    private ProgramaClaseRepository programaClaseRepository;
}
