package repository.implementation;

import data.Solution;
import org.springframework.stereotype.Repository;
import repository.AbstractBaseRepository;
import repository.template.SolutionRepository;

@Repository
public class SolutionRepositoryImpl extends AbstractBaseRepository<Solution> implements SolutionRepository {

    @Override
    public Class<Solution> getEntityType() {
        return Solution.class;
    }
}
