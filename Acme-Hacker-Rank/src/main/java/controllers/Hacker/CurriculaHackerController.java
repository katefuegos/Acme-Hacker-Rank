
package controllers.Hacker;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import security.UserAccount;
import services.ConfigurationService;
import services.CurriculaService;
import services.EducationDataService;
import services.HackerService;
import services.MiscellaneousDataService;
import services.PositionDataService;
import controllers.AbstractController;
import domain.Curricula;
import domain.EducationData;
import domain.Hacker;
import domain.MiscellaneousData;
import domain.PositionData;
import forms.CurriculaForm;

@Controller
@RequestMapping("/curricula/hacker")
public class CurriculaHackerController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private CurriculaService			curriculaService;

	@Autowired
	private HackerService				hackerService;

	@Autowired
	private ConfigurationService		configurationService;

	@Autowired
	private PositionDataService			positionDataService;

	@Autowired
	private EducationDataService		educationDataService;

	@Autowired
	private MiscellaneousDataService	miscellaneousDataService;


	// Constructor---------------------------------------------------------

	public CurriculaHackerController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		final UserAccount uA = LoginService.getPrincipal();
		final int hackerId = this.hackerService.findHackerByUseraccount(uA).getId();

		try {
			Assert.notNull(this.hackerService.findOne(hackerId));
			final Collection<Curricula> curriculas = this.curriculaService.findByHackerId(hackerId);
			result = new ModelAndView("curricula/list");
			result.addObject("curriculas", curriculas);
			result.addObject("requestURI", "curricula/list.do?hackerId=" + hackerId);
			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/hacker/list.do");
			if (this.hackerService.findOne(hackerId) == null)
				redirectAttrs.addFlashAttribute("message", "curricula.error.unexist");
		}

		return result;
	}

	@RequestMapping(value = "/listData", method = RequestMethod.GET)
	public ModelAndView listData(final int curriculaId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		final UserAccount uA = LoginService.getPrincipal();
		final int hackerId = this.hackerService.findHackerByUseraccount(uA).getId();

		try {
			Assert.notNull(this.hackerService.findOne(hackerId));
			final Collection<PositionData> positiondatas = this.positionDataService.findByCurriculaId(curriculaId);
			final Collection<EducationData> educationdatas = this.educationDataService.findByCurriculaId(curriculaId);
			final Collection<MiscellaneousData> miscellaneousdatas = this.miscellaneousDataService.findByCurriculaId(curriculaId);
			result = new ModelAndView("curricula/listData");
			result.addObject("positiondatas", positiondatas);
			result.addObject("educationdatas", educationdatas);
			result.addObject("miscellaneousdatas", miscellaneousdatas);
			result.addObject("requestURI", "curricula/hacker/listData.do?curriculaId=" + curriculaId);
			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/hacker/list.do");
			if (this.hackerService.findOne(hackerId) == null)
				redirectAttrs.addFlashAttribute("message", "curricula.error.unexist");
		}
		return result;
	}

	// CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final RedirectAttributes redirectAttrs) {
		ModelAndView result = null;
		final Collection<Hacker> hackers = new ArrayList<Hacker>();
		try {
			final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
			final CurriculaForm curriculaForm = new CurriculaForm();
			curriculaForm.setId(0);

			result = this.createModelAndView(curriculaForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/curricula/hacker/list.do");
			if (hackers.isEmpty())
				redirectAttrs.addFlashAttribute("message", "curricula.error.noPositions");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CurriculaForm curriculaForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(curriculaForm, "curricula.commit.error");
		else
			try {
				final Curricula curricula = this.curriculaService.create();
				curricula.setFullName(curriculaForm.getFullName());
				curricula.setStatement(curriculaForm.getStatement());
				curricula.setPhoneNumber(curriculaForm.getPhoneNumber());
				curricula.setGithubProfile(curriculaForm.getGithubProfile());
				curricula.setLinkedinprofile(curriculaForm.getLinkedInProfile());
				curricula.setHacker(curriculaForm.getHacker());

				this.curriculaService.save(curricula);

				result = new ModelAndView("redirect:/curricula/hacker/list.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView(curriculaForm, "curricula.commit.error");
			}
		return result;
	}

	// EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int curriculaId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Curricula curricula;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		final Collection<Hacker> hackers = new ArrayList<Hacker>();
		try {
			curricula = this.curriculaService.findOne(curriculaId);
			Assert.notNull(curricula);

			final CurriculaForm curriculaForm = new CurriculaForm();

			curriculaForm.setId(curricula.getId());
			curriculaForm.setFullName(curricula.getFullName());
			curriculaForm.setStatement(curricula.getStatement());
			curriculaForm.setPhoneNumber(curricula.getPhoneNumber());
			curriculaForm.setGithubProfile(curricula.getGithubProfile());
			curriculaForm.setLinkedInProfile(curricula.getLinkedinprofile());
			curriculaForm.setHacker(curricula.getHacker());

			result = this.editModelAndView(curriculaForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/curricula/hacker/list.do");
			if (this.curriculaService.findOne(curriculaId) == null)
				redirectAttrs.addFlashAttribute("message", "curricula.error.unexist");
			else if (!this.curriculaService.findOne(curriculaId).getHacker().equals(b))
				redirectAttrs.addFlashAttribute("message", "curricula.error.notFromActor");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final CurriculaForm curriculaForm, final BindingResult binding) {
		ModelAndView result;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		if (binding.hasErrors())
			result = this.editModelAndView(curriculaForm, "curricula.commit.error");
		else
			try {
				Assert.notNull(curriculaForm);
				final Curricula curricula = this.curriculaService.findOne(curriculaForm.getId());
				curricula.setFullName(curriculaForm.getFullName());
				curricula.setStatement(curriculaForm.getStatement());
				curricula.setPhoneNumber(curriculaForm.getPhoneNumber());
				curricula.setGithubProfile(curriculaForm.getGithubProfile());
				curricula.setLinkedinprofile(curriculaForm.getLinkedInProfile());
				curricula.setHacker(curriculaForm.getHacker());

				this.curriculaService.save(curricula);

				result = new ModelAndView("redirect:/curricula/hacker/list.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(curriculaForm, "curricula.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final CurriculaForm curriculaForm, final BindingResult binding) {
		ModelAndView result;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		if (binding.hasErrors())
			result = this.editModelAndView(curriculaForm, "curricula.commit.error");
		else
			try {
				Assert.notNull(curriculaForm);
				final Curricula curricula = this.curriculaService.findOne(curriculaForm.getId());
				Assert.isTrue(curricula.getHacker().equals(b));

				this.curriculaService.delete(this.curriculaService.findOne(curriculaForm.getId()));

				result = new ModelAndView("redirect:/curricula/hacker/list.do");
			} catch (final Throwable oops) {

				result = this.editModelAndView(curriculaForm, "curricula.commit.error");
			}
		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int curriculaId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Curricula curricula = null;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		try {
			curricula = this.curriculaService.findOne(curriculaId);
			Assert.notNull(curricula);

			final CurriculaForm curriculaForm = new CurriculaForm();
			curriculaForm.setId(curricula.getId());
			curriculaForm.setFullName(curricula.getFullName());
			curriculaForm.setStatement(curricula.getStatement());
			curriculaForm.setPhoneNumber(curricula.getPhoneNumber());
			curriculaForm.setGithubProfile(curricula.getGithubProfile());
			curriculaForm.setLinkedInProfile(curricula.getLinkedinprofile());
			curriculaForm.setHacker(curricula.getHacker());

			result = this.ShowModelAndView(curriculaForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/curricula/hacker/list.do");
			if (this.curriculaService.findOne(curriculaId) == null)
				redirectAttrs.addFlashAttribute("message", "curricula.error.unexist");
			else if (!this.curriculaService.findOne(curriculaId).getHacker().equals(b))
				redirectAttrs.addFlashAttribute("message", "curricula.error.notFromActor");
		}
		return result;
	}

	// MODEL
	protected ModelAndView createModelAndView(final CurriculaForm curriculaForm) {
		ModelAndView result;
		result = this.createModelAndView(curriculaForm, null);
		return result;
	}

	protected ModelAndView createModelAndView(final CurriculaForm curriculaForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("curricula/create");

		final Collection<Hacker> hackers = this.hackerService.findAll();

		result.addObject("message", message);
		result.addObject("requestURI", "curricula/hacker/create.do");
		result.addObject("curriculaForm", curriculaForm);
		result.addObject("isRead", false);
		result.addObject("id", 0);
		result.addObject("hackers", hackers);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView editModelAndView(final CurriculaForm curriculaForm) {
		ModelAndView result;
		result = this.editModelAndView(curriculaForm, null);
		return result;
	}

	protected ModelAndView editModelAndView(final CurriculaForm curriculaForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("curricula/edit");

		//final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		final Collection<Hacker> hackers = this.hackerService.findAll();

		result.addObject("message", message);
		result.addObject("requestURI", "curricula/hacker/edit.do?curriculaId=" + curriculaForm.getId());
		result.addObject("curriculaForm", curriculaForm);
		result.addObject("hackers", hackers);
		result.addObject("id", curriculaForm.getId());
		result.addObject("isRead", false);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView ShowModelAndView(final CurriculaForm curriculaForm) {
		ModelAndView result;
		result = this.ShowModelAndView(curriculaForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final CurriculaForm curriculaForm, final String message) {
		final ModelAndView result;

		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());

		result = new ModelAndView("curricula/show");
		result.addObject("message", message);
		result.addObject("requestURI", "curricula/hacker/show.do?curriculaId=" + curriculaForm.getId());
		result.addObject("curriculaForm", curriculaForm);
		result.addObject("id", curriculaForm.getId());
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}
}
