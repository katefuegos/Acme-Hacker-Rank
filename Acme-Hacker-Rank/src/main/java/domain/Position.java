package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Position extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String ticker;
	private String title;
	private String description;
	private Date deadline;
	private String skills;
	private String profile;
	private String tecnologies;
	private Double salary;
	private boolean draftmode;

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "")
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@NotBlank
	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	@NotBlank
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@NotBlank
	public String getTecnologies() {
		return tecnologies;
	}

	public void setTecnologies(String tecnologies) {
		this.tecnologies = tecnologies;
	}

	@NotNull
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@NotNull
	public boolean isDraftmode() {
		return draftmode;
	}

	public void setDraftmode(boolean draftmode) {
		this.draftmode = draftmode;
	}

	// Relationships ---------------------------------------------------------
	private Company company;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
