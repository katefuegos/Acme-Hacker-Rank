package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Problem extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String title;
	private String statement;
	private String hint;
	private String attachments;
	private boolean draftmode;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	@NotBlank
	@URL
	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	@NotNull
	public boolean isDraftmode() {
		return draftmode;
	}

	public void setDraftmode(boolean draftmode) {
		this.draftmode = draftmode;
	}

	// Relationships ---------------------------------------------------------
	private Position position;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
