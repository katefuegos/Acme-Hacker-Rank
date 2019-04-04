package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MiscellaneousDataRepository;
import domain.MiscellaneousData;

@Service
@Transactional
public class MiscellaneousDataService {

	// Repository-----------------------------------------------

	@Autowired
	private MiscellaneousDataRepository miscellaneousDataRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public MiscellaneousDataService() {

		super();
	}

	// Simple CRUD----------------------------------------------

	public MiscellaneousData create() {
		final MiscellaneousData miscellaneousData = new MiscellaneousData();

		return miscellaneousData;
	}

	public List<MiscellaneousData> findAll() {
		return this.miscellaneousDataRepository.findAll();
	}

	public MiscellaneousData findOne(final Integer miscellaneousDataId) {
		return this.miscellaneousDataRepository.findOne(miscellaneousDataId);
	}

	public MiscellaneousData save(final MiscellaneousData miscellaneousData) {
		Assert.notNull(miscellaneousData);

		final MiscellaneousData saved = this.miscellaneousDataRepository
				.save(miscellaneousData);
		return saved;
	}

	public void delete(final MiscellaneousData miscellaneousData) {
		this.miscellaneousDataRepository.delete(miscellaneousData);
	}

	// Other Methods--------------------------------------------

	public MiscellaneousData copy(int miscellaneousDataId) {
		Assert.notNull(miscellaneousDataId);
		MiscellaneousData miscellaneousData = this.findOne(miscellaneousDataId);
		Assert.notNull(miscellaneousData);

		final MiscellaneousData miscellaneousDataCopy = new MiscellaneousData();
		miscellaneousDataCopy.setText(miscellaneousData.getText());
		miscellaneousDataCopy
				.setAttachments(miscellaneousData.getAttachments());

		return miscellaneousDataCopy;
	}
	
	public Collection<MiscellaneousData> findByCurriculaId(int curriculaId){
		Assert.notNull(curriculaId);
		return miscellaneousDataRepository.findByCurriculaId(curriculaId);
	}
}
