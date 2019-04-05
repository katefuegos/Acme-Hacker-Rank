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
	private CurriculaRepository curriculaRepository;

	// Services-------------------------------------------------

	@Autowired
	private PositionDataService positionDataService;

	@Autowired
	private EducationDataService educationDataService;

	@Autowired
	private MiscellaneousDataService miscellaneousDataService;

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

			Collection<EducationData> educationdatas = educationDataService
					.findByCurriculaId(curricula.getId());
			Collection<MiscellaneousData> miscellaneousdatas = miscellaneousDataService
					.findByCurriculaId(curricula.getId());
			Collection<PositionData> positiondatas = positionDataService
					.findByCurriculaId(curricula.getId());

			if (!educationdatas.isEmpty()) {
				for (EducationData e : educationdatas) {
					educationDataService.delete(e);
				}
			}

			if (!miscellaneousdatas.isEmpty()) {
				for (MiscellaneousData m : miscellaneousdatas) {
					miscellaneousDataService.delete(m);
				}
			}

			if (!positiondatas.isEmpty()) {
				for (PositionData p : positiondatas) {
					positionDataService.delete(p);
				}
			}
			this.curriculaRepository.delete(curricula);
		}
	}

	// Other Methods--------------------------------------------

	public Curricula copy(int curriculaId) {
		Assert.notNull(curriculaId);
		Curricula curricula = this.findOne(curriculaId);
		Assert.notNull(curricula);

		Curricula curriculaCopy = new Curricula();
		curriculaCopy.setCopy(true);
		curriculaCopy.setFullName(curricula.getFullName());
		curriculaCopy.setGithubProfile(curricula.getGithubProfile());
		curriculaCopy.setHacker(curricula.getHacker());
		curriculaCopy.setLinkedinprofile(curricula.getLinkedinprofile());
		curriculaCopy.setPhoneNumber(curricula.getPhoneNumber());
		curriculaCopy.setStatement(curricula.getStatement());
		Curricula result = this.save(curriculaCopy);

		Collection<EducationData> educationDataList = educationDataService
				.findByCurriculaId(curriculaId);
		if (!educationDataList.isEmpty()) {
			for (EducationData e : educationDataList) {
				EducationData copy = educationDataService.copy(e.getId());
				copy.setCurricula(result);
				educationDataService.save(copy);
			}
		}

		Collection<PositionData> positionDataList = positionDataService
				.findByCurriculaId(curriculaId);
		if (!positionDataList.isEmpty()) {
			for (PositionData e : positionDataList) {
				PositionData copy = positionDataService.copy(e.getId());
				copy.setCurricula(result);
				positionDataService.save(copy);
			}
		}

		Collection<MiscellaneousData> miscellaneousDataList = miscellaneousDataService
				.findByCurriculaId(curriculaId);
		if (!miscellaneousDataList.isEmpty()) {
			for (MiscellaneousData e : miscellaneousDataList) {
				MiscellaneousData copy = miscellaneousDataService.copy(e
						.getId());
				copy.setCurricula(result);
				miscellaneousDataService.save(copy);
			}
		}

		return result;
	}

	public Collection<Curricula> findByHackerId(int hackerId) {
		Assert.notNull(hackerId);
		return curriculaRepository.findByHackerId(hackerId);
	}
}
