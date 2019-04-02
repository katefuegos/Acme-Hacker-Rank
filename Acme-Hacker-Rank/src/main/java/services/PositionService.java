
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import security.LoginService;
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
		position.setTicker(this.generateTicker());
		position.setDraftmode(true);
		position.setCompany(this.companyService.findCompanyByUseraccount(LoginService.getPrincipal()));

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
			Assert.isTrue(this.problemService.findByPositionId(position.getId()).size() >= 2);

		final Position saved = this.positionRepository.save(position);
		return saved;
	}

	public void delete(final Position position) {
		this.positionRepository.delete(position);
	}

	// Other Methods--------------------------------------------
	public Collection<Position> findFinalMode() {
		final Collection<Position> result = this.positionRepository.findFinalMode();

		return result;
	}

	@SuppressWarnings("deprecation")
	public String generateTicker() {
		final Date date = new Date();
		final Integer s1 = date.getDate();
		String day = s1.toString();
		if (day.length() == 1)
			day = "0" + day;
		final Integer s2 = date.getMonth() + 1;
		String month = s2.toString();
		if (month.length() == 1)
			month = "0" + month;
		final Integer s3 = date.getYear();
		final String year = s3.toString().substring(1);

		return year + month + day + "-" + this.generateStringAux();
	}

	private String generateStringAux() {
		final int length = 5;
		final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		final Random rng = new Random();
		final char[] text = new char[length];
		for (int i = 0; i < 5; i++)
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		return new String(text);
	}
}
