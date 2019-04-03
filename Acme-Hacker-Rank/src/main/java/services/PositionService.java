
package services;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import security.LoginService;
import domain.Company;
import domain.Position;

@Service
@Transactional
public class PositionService {

	// Repository-----------------------------------------------

	@Autowired
	private PositionRepository	positionRepository;

	// Services-------------------------------------------------

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private ProblemService		problemService;


	// Constructor----------------------------------------------

	public PositionService() {

		super();
	}

	// Simple CRUD----------------------------------------------

	public Position create() {
		final Position position = new Position();

		final domain.Company company = this.companyService.findCompanyByUseraccount(LoginService.getPrincipal());

		position.setTicker(this.generateTicker(company));
		position.setDraftmode(true);
		position.setCompany(company);

		return position;
	}

	public List<Position> findAll() {
		return this.positionRepository.findAll();
	}

	public Position findOne(final Integer positionId) {
		return this.positionRepository.findOne(positionId);
	}

	public Position save(final Position position) {
		Assert.notNull(position);
		if (position.isDraftmode() == false)
			Assert.isTrue(this.problemService.findByPositionId(position.getId()).size() >= 2, "position.error.noProblem");

		final Position saved = this.positionRepository.save(position);
		return saved;
	}

	public void delete(final Position position) {

		this.positionRepository.delete(position);
	}

	// Other Methods--------------------------------------------
	public Position cancel(final Position position) {
		Assert.notNull(position);
		final Company company = this.companyService.findCompanyByUseraccountId(LoginService.getPrincipal().getId());
		Assert.notNull(company);
		Assert.isTrue(position.getCompany().getId() == company.getId());
		Assert.isTrue(!position.isDraftmode(), "position.error.draftmode");

		position.setCancelled(true);

		final Position saved = this.positionRepository.save(position);
		return saved;
	}

	public Collection<Position> search(final String keyword) {
		final Collection<Position> result = this.positionRepository.search(keyword);

		return result;
	}

	public Collection<Position> findFinalMode() {
		final Collection<Position> result = this.positionRepository.findFinalMode();

		return result;
	}

	public Collection<Position> findFinalByCompany(final Company company) {
		Assert.notNull(company);

		return this.positionRepository.findFinalByCompany(company.getId());

	}

	public Collection<Position> findDraftByCompany(final Company company) {
		Assert.notNull(company);

		return this.positionRepository.findDraftByCompany(company.getId());
	}

	public Position reconstruct(final Position position, final forms.PositionForm positionForm) {

		position.setDeadline(positionForm.getDeadline());
		position.setDescription(positionForm.getDescription());
		position.setDraftmode(positionForm.isDraftmode());
		position.setProfile(positionForm.getProfile());
		position.setSalary(positionForm.getSalary());
		position.setSkills(positionForm.getSkills());
		position.setTecnologies(positionForm.getTecnologies());
		position.setTitle(positionForm.getTitle());

		return position;
	}

	public String generateTicker(final Company company) {
		final String initials = company.getComercialName().substring(0, 4);
		String number;

		final Random rng = new Random();
		final StringBuilder sb = new StringBuilder();
		while (sb.length() < 4)
			sb.append(rng.nextInt(10));
		number = sb.toString();

		return initials + "-" + number;
	}

}
