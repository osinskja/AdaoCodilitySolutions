import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import repository.implementation.SolutionRepositoryImpl;
import repository.template.SolutionRepository;

@SpringBootApplication
@EntityScan("data")
public class TestRepositoryApplication {
    @Bean
    public SolutionRepository solutionRepository() {
        return new SolutionRepositoryImpl();
    }
}
