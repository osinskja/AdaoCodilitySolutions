package repository;

import common.AbstractRepositoryTest;
import data.Solution;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import repository.template.SolutionRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionRepositoryTest extends AbstractRepositoryTest {

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

}
