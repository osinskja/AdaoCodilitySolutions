package controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import repository.template.SolutionRepository;

import static org.mockito.Mockito.mock;

@SpringBootApplication
public class TestControllerApplication {
    @Bean
    public SolutionRepository mockSolutionRepository() {
        return mock(SolutionRepository.class);
    }
}
