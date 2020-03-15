package controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.mock;

@SpringBootApplication
public class TestControllerApplication {
    @Bean
    JdbcTemplate jdbcTemplate(){
        return mock(JdbcTemplate.class);
    }
}
