package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PositionDataRepository;
import domain.PositionData;

@Service
@Transactional
public class PositionDataService {

	// Repository-----------------------------------------------

	@Autowired
	private PositionDataRepository positionDataRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public PositionDataService() {

		super();
	}

	// Simple CRUD----------------------------------------------

	public PositionData create() {
		final PositionData positionData = new PositionData();

		return positionData;
	}

	public List<PositionData> findAll() {
		return this.positionDataRepository.findAll();
	}

	public PositionData findOne(final Integer positionDataId) {
		return this.positionDataRepository.findOne(positionDataId);
	}

	public PositionData save(final PositionData positionData) {
		Assert.notNull(positionData);

		final PositionData saved = this.positionDataRepository
				.save(positionData);
		return saved;
	}

	public void delete(final PositionData positionData) {
		this.positionDataRepository.delete(positionData);
	}

	// Other Methods--------------------------------------------

	public PositionData copy(int positionDataId) {
		Assert.notNull(positionDataId);
		PositionData positionData = this.findOne(positionDataId);
		Assert.notNull(positionData);

		final PositionData positionDataCopy = new PositionData();
		positionDataCopy.setDescription(positionData.getDescription());
		positionDataCopy.setEndDate(positionData.getEndDate());
		positionDataCopy.setStartDate(positionData.getStartDate());
		positionDataCopy.setTitle(positionData.getTitle());

		return this.save(positionDataCopy);
	}
	
	public Collection<PositionData> findByCurriculaId(int curriculaId){
		Assert.notNull(curriculaId);
		return positionDataRepository.findByCurriculaId(curriculaId);
	}
}
