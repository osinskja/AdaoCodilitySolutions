package common;

import org.junit.ClassRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@ContextConfiguration(
        initializers = {AbstractRepositoryTest.Initializer.class},
        classes = {
                TestRepositoryApplication.class
        }
)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AbstractRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:9.4")
            .withDatabaseName("containerDB")
            .withUsername("user1")
            .withPassword("password");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected void tearDown(Class... entities) throws Exception {
        for (Class entity : entities) {
            jdbcTemplate.execute("DELETE FROM " + entity.getSimpleName() + ";");
        }
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
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
