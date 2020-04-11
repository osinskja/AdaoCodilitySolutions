import data.Solution;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import repository.template.SolutionRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(
        initializers = {SolutionRepositoryTest.Initializer.class},
        classes = {TestRepositoryApplication.class}
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class SolutionRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:9.4")
            .withDatabaseName("containerDB")
            .withUsername("user")
            .withPassword("password");

    @Autowired
    private SolutionRepository solutionRepository;

    @Test
    void shouldBeRunningPostgresDb() {
        assertTrue(postgresqlContainer.isRunning());
    }

    @Test
    void shouldPersistSolution() {
        Solution solution = new Solution();
        solution.setCode("testCode");
        solution.setSolutionDescription("test solution description");
        solution.setProblemDescription("test problem description");
        solution.setTitle("test title");

       solutionRepository.persistEntity(solution);

       assertThat(solutionRepository.findAll()).contains(solution);
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgresqlContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresqlContainer.getUsername(),
                    "spring.datasource.password=" + postgresqlContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
