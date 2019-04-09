
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import domain.Curricula;
import domain.EducationData;
import domain.MiscellaneousData;
import domain.PositionData;

@Service
@Transactional
public class CurriculaService {

	// Repository-----------------------------------------------

	@Autowired
	private CurriculaRepository			curriculaRepository;

	// Services-------------------------------------------------

	@Autowired
	private PositionDataService			positionDataService;

	@Autowired
	private EducationDataService		educationDataService;

	@Autowired
	private MiscellaneousDataService	miscellaneousDataService;


	// Constructor----------------------------------------------

	public CurriculaService() {

		super();
	}

	// Simple CRUD----------------------------------------------

	public Curricula create() {
		final Curricula curricula = new Curricula();

		curricula.setCopy(false);
		return curricula;
	}

	public List<Curricula> findAll() {
		return this.curriculaRepository.findAll();
	}

	public Curricula findOne(final Integer curriculaId) {
		return this.curriculaRepository.findOne(curriculaId);
	}

	public Curricula save(final Curricula curricula) {
		Assert.notNull(curricula);

		final Curricula saved = this.curriculaRepository.save(curricula);
		return saved;
	}

	public void delete(final Curricula curricula) {
		if (curricula.isCopy() == false) {

			final Collection<EducationData> educationdatas = this.educationDataService.findByCurriculaId(curricula.getId());
			final Collection<MiscellaneousData> miscellaneousdatas = this.miscellaneousDataService.findByCurriculaId(curricula.getId());
			final Collection<PositionData> positiondatas = this.positionDataService.findByCurriculaId(curricula.getId());

			if (!educationdatas.isEmpty())
				for (final EducationData e : educationdatas)
					this.educationDataService.delete(e);

			if (!miscellaneousdatas.isEmpty())
				for (final MiscellaneousData m : miscellaneousdatas)
					this.miscellaneousDataService.delete(m);

			if (!positiondatas.isEmpty())
				for (final PositionData p : positiondatas)
					this.positionDataService.delete(p);
			this.curriculaRepository.delete(curricula);
		}
	}

	// Other Methods--------------------------------------------

	public Curricula copy(final int curriculaId) {
		Assert.notNull(curriculaId);
		final Curricula curricula = this.findOne(curriculaId);
		Assert.notNull(curricula);

		final Curricula curriculaCopy = new Curricula();
		curriculaCopy.setCopy(true);
		curriculaCopy.setFullName(curricula.getFullName());
		curriculaCopy.setGithubProfile(curricula.getGithubProfile());
		curriculaCopy.setHacker(curricula.getHacker());
		curriculaCopy.setLinkedinprofile(curricula.getLinkedinprofile());
		curriculaCopy.setPhoneNumber(curricula.getPhoneNumber());
		curriculaCopy.setStatement(curricula.getStatement());
		final Curricula result = this.save(curriculaCopy);

		final Collection<EducationData> educationDataList = this.educationDataService.findByCurriculaId(curriculaId);
		if (!educationDataList.isEmpty())
			for (final EducationData e : educationDataList) {
				final EducationData copy = this.educationDataService.copy(e.getId());
				copy.setCurricula(result);
				this.educationDataService.save(copy);
			}

		final Collection<PositionData> positionDataList = this.positionDataService.findByCurriculaId(curriculaId);
		if (!positionDataList.isEmpty())
			for (final PositionData e : positionDataList) {
				final PositionData copy = this.positionDataService.copy(e.getId());
				copy.setCurricula(result);
				this.positionDataService.save(copy);
			}

		final Collection<MiscellaneousData> miscellaneousDataList = this.miscellaneousDataService.findByCurriculaId(curriculaId);
		if (!miscellaneousDataList.isEmpty())
			for (final MiscellaneousData e : miscellaneousDataList) {
				final MiscellaneousData copy = this.miscellaneousDataService.copy(e.getId());
				copy.setCurricula(result);
				this.miscellaneousDataService.save(copy);
			}

		return result;
	}

	public Collection<Curricula> findByHackerId(final int hackerId) {
		Assert.notNull(hackerId);
		return this.curriculaRepository.findByHackerId(hackerId);
	}

	public Collection<Curricula> findNoCopies(final int hackerId) {
		Assert.notNull(hackerId);
		return this.curriculaRepository.findNoCopies(hackerId);
	}
}
