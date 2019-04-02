package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Box;

@Service
@Transactional
public class BoxService {

	// Repository-------------------------------------------------------------------------

	@Autowired
	private BoxRepository boxRepository;

	// Services---------------------------------------------------------------------------
	@Autowired
	private ActorService actorService;

	// Constructor------------------------------------------------------------------------

	public BoxService() {
		super();
	}

	// Simple
	// CRUD------------------------------------------------------------------------
	public Box create() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount,
				"Debe estar logeado en el sistema para crear una carpeta");
		final Actor actor = this.actorService.findByUserAccount(userAccount);

		final Box box = new Box();

		final String name = "";

		box.setName(name);
		box.setActor(actor);

		return box;

	}

	public Collection<Box> findAll() {
		Collection<Box> boxes;

		boxes = this.boxRepository.findAll();
		Assert.notNull(boxes);

		return boxes;
	}

	public Box findOne(final Integer boxId) {
		return this.boxRepository.findOne(boxId);
	}

	public Box save(final Box box) {

		final Box saved = this.boxRepository.save(box);
		return saved;
	}

	public void delete(final Box box) {

		this.boxRepository.delete(box);
	}

	// Other
	// Methods---------------------------------------------------------------------------

	public Box findBoxByActorId(final int actorId) {
		return this.boxRepository.findBoxByActorId(actorId);
	}
}
