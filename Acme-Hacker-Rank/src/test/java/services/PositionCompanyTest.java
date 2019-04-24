/*
 * RegistrationTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Position;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PositionCompanyTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private PositionService	positionService;


	// Tests ------------------------------------------------------------------
	@Test
	public void driverCreatePosition() {
		this.authenticate("company1");

		final String title = "Network Administrator";
		final String description = "Create a new network tokenring";
		final Date deadline = new Date(System.currentTimeMillis() + 64800);
		final String skills = "Proactive";
		final String profile = "Network analyst";
		final String tecnologies = "Nmap, wireshark, Lora";
		final Double salary = 4000.0;

		final boolean draftmode1 = true;
		final boolean draftmode2 = false;

		final Object testingData[][] = {

			/*
			 * a) Functional requirements - 9.1 - Create a new Position by company
			 * b) Positive tests -
			 * c) analysis of sentence coverage: 89.2%
			 * d) analysis of data coverage. The position position1 is being modified with the
			 * following data: status = "ACCEPTED", draft mode = "false" The actor
			 * in charge is: company1
			 */
			{
				title, description, deadline, skills, profile, tecnologies, salary, draftmode1, null, "company1", null
			},

			/*
			 * a) Functional requirements - 9.1 - Create a new Position by company
			 * b) Negative tests - Business rule: You can not create a new position in final mode without assigning two problems.
			 * c) analysis of sentence coverage: 89.2%
			 * d) analysis of data coverage. The position position2 is being modified with the following
			 * data: status = "ACCEPTED", draft mode = "false" The actor in charge
			 * is: company2
			 */
			{
				title, description, deadline, skills, profile, tecnologies, salary, draftmode2, null, "company2", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateManage((String) testingData[i][0], (String) testingData[i][1], (Date) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Double) testingData[i][6],
					(boolean) testingData[i][7], (String) testingData[i][8], (String) testingData[i][9], (Class<?>) testingData[i][10]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateManage(final String title, final String description, final Date deadline, final String skills, final String profile, final String tecnologies, final Double salary, final boolean draftmode, final String namePosition,
		final String username, final Class<?> expected) {
		Class<?> caught;
		final int positionId;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			Position position;
			if (namePosition != null) {
				positionId = super.getEntityId(namePosition);
				position = this.positionService.findOne(positionId);
			} else
				position = this.positionService.create();

			position.setTitle(title);
			position.setDescription(description);
			position.setDeadline(deadline);
			position.setSkills(skills);
			position.setProfile(profile);
			position.setTecnologies(tecnologies);
			position.setSalary(salary);
			position.setDraftmode(draftmode);

			this.positionService.save(position);

			super.unauthenticate();
			this.positionService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}