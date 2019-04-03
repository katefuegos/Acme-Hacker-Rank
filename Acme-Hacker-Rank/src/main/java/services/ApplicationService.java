package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.LoginService;
import domain.Application;
import domain.Curricula;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class ApplicationService {

	// Repository-----------------------------------------------

	@Autowired
	private ApplicationRepository applicationRepository;

	// Services-------------------------------------------------

	@Autowired
	private PositionService positionService;

	@Autowired
	private ProblemService problemService;

	@Autowired
	private HackerService hackerService;

	@Autowired
	private CurriculaService curriculaService;

	// Constructor----------------------------------------------

	public ApplicationService() {

		super();
	}

	// Simple CRUD----------------------------------------------

	public Application create(int positionId, int curriculaId) {
		final Application application = new Application();
		Assert.notNull(positionId);
		Position position = positionService.findOne(positionId);
		Assert.notNull(position);
		Assert.isTrue(position.isDraftmode() == false);

		application.setPosition(position);

		Collection<Problem> problems = problemService.findByPositionId(position
				.getId());
		Problem problem = (Problem) getRandomObject(problems);
		application.setProblem(problem);

		Assert.notNull(positionId);
		Curricula curricula = curriculaService.findOne(curriculaId);
		Assert.notNull(curricula);
		Curricula copy = curriculaService.copy(curricula.getId());
		application.setCurricula(copy);

		application.setStatus("PENDING");

		application.setHacker(hackerService
				.findHackerByUseraccount(LoginService.getPrincipal()));

		return application;
	}

	public List<Application> findAll() {
		return this.applicationRepository.findAll();
	}

	public Application findOne(final Integer applicationId) {
		return this.applicationRepository.findOne(applicationId);
	}

	public Application save(final Application application) {
		Assert.notNull(application);
		if (application.getId() == 0) {
			application.setPublicationMoment(new Date(System
					.currentTimeMillis() - 1000));
			Assert.isTrue(application.getCurricula().isCopy() == true);
		}

		final Application saved = this.applicationRepository.save(application);
		return saved;
	}

	public void delete(final Application application) {
		this.applicationRepository.delete(application);
	}

	// Other Methods--------------------------------------------

	private Object getRandomObject(Collection<Problem> problems) {
		Random rnd = new Random();
		int i = rnd.nextInt(problems.size());
		return problems.toArray()[i];
	}

	public Collection<Application> findByCompanyId(int companyId) {
		Assert.notNull(companyId);
		return applicationRepository.findByCompanyId(companyId);
	}

	public Collection<Application> findByHackerId(int hackerId) {
		Assert.notNull(hackerId);
		return applicationRepository.findByHackerId(hackerId);
	}
}
