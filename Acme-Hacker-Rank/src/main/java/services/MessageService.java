package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Repository-------------------------------------------------------------------------

	@Autowired
	private MessageRepository messageRepository;

	// Services---------------------------------------------------------------------------
	@Autowired
	private BoxService boxService;

	@Autowired
	private ActorService actorService;

	// Constructor------------------------------------------------------------------------

	public MessageService() {
		super();
	}

	// Simple
	// CRUD------------------------------------------------------------------------

	public Message create() {
		final Message message = new Message();
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount,
				"Debe estar logeado en el sistema para crear una carpeta");
		final Actor actor = this.actorService.findByUserAccount(userAccount);

		final String subject = "";
		final String body = "";

		final Box box = this.boxService.findBoxByActorId(actor.getId());
		final Actor sender = actorService.findByUserAccount(LoginService
				.getPrincipal());

		message.setSubject(subject);
		message.setBody(body);
		message.setMoment(new Date(System.currentTimeMillis() - 1000));
		message.setBox(box);
		message.setSender(sender);

		return message;

	}

	public Collection<Message> findAll() {
		final Collection<Message> messages = this.messageRepository.findAll();
		Assert.notNull(messages);
		return messages;
	}

	public Message findOne(final Integer messageId) {
		return this.messageRepository.findOne(messageId);
	}

	public Message save(final Message message) {
		Message saved = this.messageRepository.save(message);

		return saved;
	}

	public void delete(final Message message) {

		this.messageRepository.delete(message);

	}

	// Other
	// Methods---------------------------------------------------------------------------

	public Collection<Message> findByBox(final Box box) {
		Assert.notNull(box, "findByBox - Box must not be null");

		final Collection<Message> result = this.messageRepository
				.findByBoxId(box.getId());

		return result;

	}
}
