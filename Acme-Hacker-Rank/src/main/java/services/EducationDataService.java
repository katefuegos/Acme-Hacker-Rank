package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EducationDataRepository;
import domain.EducationData;

@Service
@Transactional
public class EducationDataService {

	// Repository-----------------------------------------------

	@Autowired
	private EducationDataRepository educationDataRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public EducationDataService() {

		super();
	}

	// Simple CRUD----------------------------------------------

	public EducationData create() {
		final EducationData educationData = new EducationData();

		return educationData;
	}

	public List<EducationData> findAll() {
		return this.educationDataRepository.findAll();
	}

	public EducationData findOne(final Integer educationDataId) {
		return this.educationDataRepository.findOne(educationDataId);
	}

	public EducationData save(final EducationData educationData) {
		Assert.notNull(educationData);

		final EducationData saved = this.educationDataRepository
				.save(educationData);
		return saved;
	}

	public void delete(final EducationData educationData) {
		this.educationDataRepository.delete(educationData);
	}

	// Other Methods--------------------------------------------

	public EducationData copy(int educationDataId) {
		Assert.notNull(educationDataId);
		EducationData educationData = this.findOne(educationDataId);
		Assert.notNull(educationData);

		final EducationData educationDataCopy = new EducationData();
		educationDataCopy.setDegree(educationData.getDegree());
		educationDataCopy.setEndDate(educationData.getEndDate());
		educationDataCopy.setStartDate(educationData.getStartDate());
		educationDataCopy.setInstitution(educationData.getInstitution());
		educationDataCopy.setMark(educationData.getMark());

		return this.save(educationDataCopy);
	}
	
	public Collection<EducationData> findByCurriculaId(int curriculaId){
		Assert.notNull(curriculaId);
		return educationDataRepository.findByCurriculaId(curriculaId);
	}
}
