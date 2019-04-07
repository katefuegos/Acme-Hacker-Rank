
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
import services.HackerService;
import services.PositionDataService;
import controllers.AbstractController;
import domain.Curricula;
import domain.Hacker;
import domain.PositionData;
import forms.PositionDataForm;

@Controller
@RequestMapping("/positionData/hacker")
public class PositionDataController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private PositionDataService		positionDataService;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private CurriculaService		curriculaService;


	// Constructor---------------------------------------------------------

	public PositionDataController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final RedirectAttributes redirectAttrs, final int curriculaId) {
		ModelAndView result;

		try {

			final Integer hackerId = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal()).getId();
			Assert.notNull(this.hackerService.findOne(hackerId));

			result = new ModelAndView("positionData/list");
			final Collection<PositionData> positionDatas = this.positionDataService.findByCurriculaId(curriculaId);

			result.addObject("positionDatas", positionDatas);
			result.addObject("requestURI", "positionData/hacker/list.do?hackerId=" + hackerId);
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
		final PositionDataForm positionDataForm = new PositionDataForm();
		positionDataForm.setId(0);

		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		System.out.println(b.getId());
		final Collection<Curricula> curriculas = this.curriculaService.findByHackerId(b.getId());

		result = this.createModelAndView(positionDataForm);
		result.addObject("curriculas", curriculas);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PositionDataForm positionDataForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(positionDataForm, "positionData.commit.error");
		else
			try {
				final PositionData positionData = this.positionDataService.create();
				positionData.setTitle(positionDataForm.getTitle());
				positionData.setDescription(positionDataForm.getDescription());
				positionData.setStartDate(positionDataForm.getStartDate());
				positionData.setEndDate(positionDataForm.getEndDate());
				positionData.setCurricula(positionDataForm.getCurricula());

				this.positionDataService.save(positionData);

				result = new ModelAndView("redirect:/curricula/hacker/listData.do?curriculaId=" + positionData.getCurricula().getId());
			} catch (final Throwable oops) {
				result = this.createModelAndView(positionDataForm, "positionData.commit.error");
			}
		return result;
	}
	// EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int positionDataId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		PositionData positionData;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		final Collection<Curricula> curriculas = this.curriculaService.findByHackerId(b.getId());

		try {
			positionData = this.positionDataService.findOne(positionDataId);
			Assert.notNull(positionData);
			Assert.isTrue(this.positionDataService.findOne(positionDataId).getCurricula().getHacker().equals(b));

			final PositionDataForm positionDataForm = new PositionDataForm();
			positionDataForm.setId(positionData.getId());
			positionDataForm.setTitle(positionData.getTitle());
			positionDataForm.setDescription(positionData.getDescription());
			positionDataForm.setStartDate(positionData.getStartDate());
			positionDataForm.setEndDate(positionData.getEndDate());
			positionDataForm.setCurricula(positionData.getCurricula());

			result = this.editModelAndView(positionDataForm);
			result.addObject("curriculas", curriculas);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/positionData/hacker/list.do");
			if (this.positionDataService.findOne(positionDataId) == null)
				redirectAttrs.addFlashAttribute("message", "positionData.error.unexist");
			else if (!this.positionDataService.findOne(positionDataId).getCurricula().getHacker().equals(b))
				redirectAttrs.addFlashAttribute("message", "positionData.error.notFromActor");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final PositionDataForm positionDataForm, final BindingResult binding) {
		ModelAndView result;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		if (binding.hasErrors())
			result = this.editModelAndView(positionDataForm, "positionData.commit.error");
		else
			try {
				Assert.notNull(positionDataForm);
				final PositionData positionData = this.positionDataService.findOne(positionDataForm.getId());
				Assert.isTrue(positionData.getCurricula().getHacker().equals(b));
				positionData.setTitle(positionDataForm.getTitle());
				positionData.setDescription(positionDataForm.getDescription());
				positionData.setEndDate(positionDataForm.getEndDate());
				positionData.setStartDate(positionDataForm.getStartDate());

				this.positionDataService.save(positionData);
				result = new ModelAndView("redirect:/curricula/hacker/listData.do?curriculaId=" + positionData.getCurricula().getId());
			} catch (final Throwable oops) {
				result = this.editModelAndView(positionDataForm, "positionData.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final PositionDataForm positionDataForm, final BindingResult binding) {
		ModelAndView result;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		if (binding.hasErrors())
			result = this.editModelAndView(positionDataForm, "positionData.commit.error");
		else
			try {
				Assert.notNull(positionDataForm);
				final PositionData positionData = this.positionDataService.findOne(positionDataForm.getId());
				Assert.isTrue(positionData.getCurricula().getHacker().equals(b));

				this.positionDataService.delete(this.positionDataService.findOne(positionDataForm.getId()));

				result = new ModelAndView("redirect:/curricula/hacker/listData.do?curriculaId=" + positionData.getCurricula().getId());
			} catch (final Throwable oops) {

				result = this.editModelAndView(positionDataForm, "positionData.commit.error");
			}
		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int positionDataId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		PositionData positionData = null;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		final Collection<Curricula> curriculas = this.curriculaService.findByHackerId(b.getId());
		try {
			positionData = this.positionDataService.findOne(positionDataId);
			Assert.notNull(positionData);
			Assert.isTrue(positionData.getCurricula().getHacker().getId() == b.getId());

			final PositionDataForm positionDataForm = new PositionDataForm();
			positionDataForm.setId(positionData.getId());
			positionDataForm.setTitle(positionData.getTitle());
			positionDataForm.setDescription(positionData.getDescription());
			positionDataForm.setStartDate(positionData.getStartDate());
			positionDataForm.setEndDate(positionData.getEndDate());

			result = this.ShowModelAndView(positionDataForm);
			result.addObject("curriculas", curriculas);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/positionData/hacker/list.do");
			if (this.positionDataService.findOne(positionDataId) == null)
				redirectAttrs.addFlashAttribute("message", "positionData.error.unexist");
			else if (!this.positionDataService.findOne(positionDataId).getCurricula().getHacker().equals(b))
				redirectAttrs.addFlashAttribute("message", "positionData.error.notFromActor");
		}
		return result;
	}

	// MODEL
	protected ModelAndView createModelAndView(final PositionDataForm positionDataForm) {
		ModelAndView result;
		result = this.createModelAndView(positionDataForm, null);
		return result;
	}

	protected ModelAndView createModelAndView(final PositionDataForm positionDataForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("positionData/create");

		result.addObject("message1", message);
		result.addObject("requestURI", "positionData/hacker/create.do");
		result.addObject("positionDataForm", positionDataForm);
		result.addObject("isRead", false);
		result.addObject("id", 0);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView editModelAndView(final PositionDataForm positionDataForm) {
		ModelAndView result;
		result = this.editModelAndView(positionDataForm, null);
		return result;
	}

	protected ModelAndView editModelAndView(final PositionDataForm positionDataForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("positionData/edit");

		result.addObject("message", message);
		result.addObject("requestURI", "positionData/hacker/edit.do?positionDataId=" + positionDataForm.getId());
		result.addObject("positionDataForm", positionDataForm);
		result.addObject("isRead", false);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView ShowModelAndView(final PositionDataForm positionDataForm) {
		ModelAndView result;
		result = this.ShowModelAndView(positionDataForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final PositionDataForm positionDataForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("positionData/show");
		result.addObject("message", message);
		result.addObject("requestURI", "positionData/hacker/show.do?positionDataId=" + positionDataForm.getId());
		result.addObject("positionDataForm", positionDataForm);
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}
}
