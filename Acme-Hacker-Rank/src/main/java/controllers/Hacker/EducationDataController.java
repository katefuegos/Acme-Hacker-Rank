
package controllers.Hacker;

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
import services.ConfigurationService;
import services.CurriculaService;
import services.EducationDataService;
import services.HackerService;
import controllers.AbstractController;
import domain.Curricula;
import domain.EducationData;
import domain.Hacker;
import forms.EducationDataForm;

@Controller
@RequestMapping("/educationData/hacker")
public class EducationDataController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private EducationDataService	educationDataService;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private CurriculaService		curriculaService;


	// Constructor---------------------------------------------------------

	public EducationDataController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final RedirectAttributes redirectAttrs, final int curriculaId) {
		ModelAndView result;

		try {

			final Integer hackerId = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal()).getId();
			Assert.notNull(this.hackerService.findOne(hackerId));

			result = new ModelAndView("educationData/list");
			final Collection<EducationData> educationDatas = this.educationDataService.findByCurriculaId(curriculaId);

			result.addObject("educationDatas", educationDatas);
			result.addObject("requestURI", "educationData/hacker/list.do?hackerId=" + hackerId);
			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	// CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final EducationDataForm educationDataForm = new EducationDataForm();
		educationDataForm.setId(0);

		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		System.out.println(b.getId());
		final Collection<Curricula> curriculas = this.curriculaService.findByHackerId(b.getId());

		result = this.createModelAndView(educationDataForm);
		result.addObject("curriculas", curriculas);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EducationDataForm educationDataForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(educationDataForm, "educationData.commit.error");
		else
			try {
				final EducationData educationData = this.educationDataService.create();
				educationData.setDegree(educationDataForm.getDegree());
				educationData.setMark(educationDataForm.getMark());
				educationData.setInstitution(educationDataForm.getInstitution());
				educationData.setStartDate(educationDataForm.getStartDate());
				educationData.setEndDate(educationDataForm.getEndDate());
				educationData.setCurricula(educationDataForm.getCurricula());

				this.educationDataService.save(educationData);

				result = new ModelAndView("redirect:/curricula/hacker/listData.do?curriculaId=" + educationData.getCurricula().getId());
			} catch (final Throwable oops) {
				result = this.createModelAndView(educationDataForm, "educationData.commit.error");
			}
		return result;
	}
	// EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int educationDataId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		EducationData educationData;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		final Collection<Curricula> curriculas = this.curriculaService.findByHackerId(b.getId());
		try {
			educationData = this.educationDataService.findOne(educationDataId);
			Assert.notNull(educationData);
			Assert.isTrue(this.educationDataService.findOne(educationDataId).getCurricula().getHacker().equals(b));

			final EducationDataForm educationDataForm = new EducationDataForm();
			educationDataForm.setId(educationData.getId());
			educationDataForm.setDegree(educationData.getDegree());
			educationDataForm.setInstitution(educationData.getInstitution());
			educationDataForm.setMark(educationData.getMark());
			educationDataForm.setStartDate(educationData.getStartDate());
			educationDataForm.setEndDate(educationData.getEndDate());
			educationDataForm.setCurricula(educationData.getCurricula());

			result = this.editModelAndView(educationDataForm);
			result.addObject("curriculas", curriculas);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/educationData/hacker/list.do");
			if (this.educationDataService.findOne(educationDataId) == null)
				redirectAttrs.addFlashAttribute("message", "educationData.error.unexist");
			else if (!this.educationDataService.findOne(educationDataId).getCurricula().getHacker().equals(b))
				redirectAttrs.addFlashAttribute("message", "educationData.error.notFromActor");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final EducationDataForm educationDataForm, final BindingResult binding) {
		ModelAndView result;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		if (binding.hasErrors())
			result = this.editModelAndView(educationDataForm, "educationData.commit.error");
		else
			try {
				Assert.notNull(educationDataForm);
				final EducationData educationData = this.educationDataService.findOne(educationDataForm.getId());
				Assert.isTrue(educationData.getCurricula().getHacker().equals(b));
				educationData.setDegree(educationDataForm.getDegree());
				educationData.setInstitution(educationDataForm.getInstitution());
				educationData.setMark(educationDataForm.getMark());
				educationData.setEndDate(educationDataForm.getEndDate());
				educationData.setStartDate(educationDataForm.getStartDate());

				this.educationDataService.save(educationData);

				result = new ModelAndView("redirect:/curricula/hacker/listData.do?curriculaId=" + educationData.getCurricula().getId());
			} catch (final Throwable oops) {
				result = this.editModelAndView(educationDataForm, "educationData.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final EducationDataForm educationDataForm, final BindingResult binding) {
		ModelAndView result;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		if (binding.hasErrors())
			result = this.editModelAndView(educationDataForm, "educationData.commit.error");
		else
			try {
				Assert.notNull(educationDataForm);
				final EducationData educationData = this.educationDataService.findOne(educationDataForm.getId());
				Assert.isTrue(educationData.getCurricula().getHacker().equals(b));

				this.educationDataService.delete(this.educationDataService.findOne(educationDataForm.getId()));

				result = new ModelAndView("redirect:/curricula/hacker/listData.do?curriculaId=" + educationData.getCurricula().getId());
			} catch (final Throwable oops) {

				result = this.editModelAndView(educationDataForm, "educationData.commit.error");
			}
		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int educationDataId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		EducationData educationData = null;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		final Collection<Curricula> curriculas = this.curriculaService.findByHackerId(b.getId());
		try {
			educationData = this.educationDataService.findOne(educationDataId);
			Assert.notNull(educationData);
			Assert.isTrue(educationData.getCurricula().getHacker().getId() == b.getId());

			final EducationDataForm educationDataForm = new EducationDataForm();
			educationDataForm.setId(educationData.getId());
			educationDataForm.setDegree(educationData.getDegree());
			educationDataForm.setInstitution(educationData.getInstitution());
			educationDataForm.setMark(educationData.getMark());
			educationDataForm.setStartDate(educationData.getStartDate());
			educationDataForm.setEndDate(educationData.getEndDate());

			result = this.ShowModelAndView(educationDataForm);
			result.addObject("curriculas", curriculas);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/educationData/hacker/list.do");
			if (this.educationDataService.findOne(educationDataId) == null)
				redirectAttrs.addFlashAttribute("message", "educationData.error.unexist");
			else if (!this.educationDataService.findOne(educationDataId).getCurricula().getHacker().equals(b))
				redirectAttrs.addFlashAttribute("message", "educationData.error.notFromActor");
		}
		return result;
	}

	// MODEL
	protected ModelAndView createModelAndView(final EducationDataForm educationDataForm) {
		ModelAndView result;
		result = this.createModelAndView(educationDataForm, null);
		return result;
	}

	protected ModelAndView createModelAndView(final EducationDataForm educationDataForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("educationData/create");

		result.addObject("message1", message);
		result.addObject("requestURI", "educationData/hacker/create.do");
		result.addObject("educationDataForm", educationDataForm);
		result.addObject("isRead", false);
		result.addObject("id", 0);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView editModelAndView(final EducationDataForm educationDataForm) {
		ModelAndView result;
		result = this.editModelAndView(educationDataForm, null);
		return result;
	}

	protected ModelAndView editModelAndView(final EducationDataForm educationDataForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("educationData/edit");

		result.addObject("message", message);
		result.addObject("requestURI", "educationData/hacker/edit.do?educationDataId=" + educationDataForm.getId());
		result.addObject("educationDataForm", educationDataForm);
		result.addObject("isRead", false);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView ShowModelAndView(final EducationDataForm educationDataForm) {
		ModelAndView result;
		result = this.ShowModelAndView(educationDataForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final EducationDataForm educationDataForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("educationData/show");
		result.addObject("message", message);
		result.addObject("requestURI", "educationData/hacker/show.do?educationDataId=" + educationDataForm.getId());
		result.addObject("educationDataForm", educationDataForm);
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}
}
