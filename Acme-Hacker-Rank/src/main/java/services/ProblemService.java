package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ProblemRepository;
import domain.Problem;

@Service
@Transactional
public class ProblemService {

	// Repository-----------------------------------------------

	@Autowired
	private ProblemRepository problemRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ProblemService() {

		super();
	}

	// Simple CRUD----------------------------------------------

	public Problem create() {
		final Problem problem = new Problem();
		problem.setDraftmode(true);

		return problem;
	}

	public List<Problem> findAll() {
		return this.problemRepository.findAll();
	}

	public Problem findOne(final Integer problemId) {
		return this.problemRepository.findOne(problemId);
	}

	public Problem save(final Problem problem) {
		Assert.notNull(problem);

		final Problem saved = this.problemRepository.save(problem);
		return saved;
	}

	public void delete(final Problem problem) {
		this.problemRepository.delete(problem);
	}

	// Other Methods--------------------------------------------

	public Collection<Problem> findByPositionId(int positionId) {
		Assert.notNull(positionId);
		return problemRepository.findByPositionId(positionId);
	}
}
