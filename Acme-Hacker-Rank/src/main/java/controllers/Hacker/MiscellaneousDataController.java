
package controllers.Hacker;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.ConfigurationService;
import services.CurriculaService;
import services.HackerService;
import services.MiscellaneousDataService;
import controllers.AbstractController;
import domain.Curricula;
import domain.Hacker;
import domain.MiscellaneousData;
import forms.MiscellaneousDataForm;

@Controller
@RequestMapping("/miscellaneousData/hacker")
public class MiscellaneousDataController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private MiscellaneousDataService	miscellaneousDataService;

	@Autowired
	private HackerService				hackerService;

	@Autowired
	private ConfigurationService		configurationService;

	@Autowired
	private CurriculaService			curriculaService;


	// Constructor---------------------------------------------------------

	public MiscellaneousDataController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final RedirectAttributes redirectAttrs, final int curriculaId) {
		ModelAndView result;

		try {

			final Integer hackerId = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal()).getId();
			Assert.notNull(this.hackerService.findOne(hackerId));

			result = new ModelAndView("miscellaneousData/list");
			final Collection<MiscellaneousData> miscellaneousDatas = this.miscellaneousDataService.findByCurriculaId(curriculaId);

			result.addObject("miscellaneousDatas", miscellaneousDatas);
			result.addObject("requestURI", "miscellaneousData/hacker/list.do?hackerId=" + hackerId);
			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	// CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam("curriculaId") final Integer curriculaId) {
		//Pasarlo como parametro 
		ModelAndView result;
		final MiscellaneousDataForm miscellaneousDataForm = new MiscellaneousDataForm();
		miscellaneousDataForm.setId(0);

		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		System.out.println(b.getId());
		final Curricula curricula = this.curriculaService.findOne(curriculaId);

		result = this.createModelAndView(miscellaneousDataForm);
		result.addObject("curricula", curricula);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MiscellaneousDataForm miscellaneousDataForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(miscellaneousDataForm, "miscellaneousData.commit.error");
		else
			try {
				final MiscellaneousData miscellaneousData = this.miscellaneousDataService.create();
				miscellaneousData.setText(miscellaneousDataForm.getText());
				miscellaneousData.setAttachments(miscellaneousDataForm.getAttachments());
				miscellaneousData.setCurricula(miscellaneousDataForm.getCurricula());

				this.miscellaneousDataService.save(miscellaneousData);

				result = new ModelAndView("redirect:/curricula/hacker/listData.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView(miscellaneousDataForm, "miscellaneousData.commit.error");
			}
		return result;
	}
	// EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int miscellaneousDataId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		MiscellaneousData miscellaneousData;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		final Collection<Curricula> curriculas = this.curriculaService.findByHackerId(b.getId());
		try {
			miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);
			Assert.notNull(miscellaneousData);
			Assert.isTrue(this.miscellaneousDataService.findOne(miscellaneousDataId).getCurricula().getHacker().equals(b));

			final MiscellaneousDataForm miscellaneousDataForm = new MiscellaneousDataForm();
			miscellaneousDataForm.setId(miscellaneousData.getId());
			miscellaneousDataForm.setText(miscellaneousData.getText());
			miscellaneousDataForm.setAttachments(miscellaneousData.getAttachments());
			miscellaneousDataForm.setCurricula(miscellaneousData.getCurricula());

			result = this.editModelAndView(miscellaneousDataForm);
			result.addObject("curriculas", curriculas);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/miscellaneousData/hacker/list.do");
			if (this.miscellaneousDataService.findOne(miscellaneousDataId) == null)
				redirectAttrs.addFlashAttribute("message", "miscellaneousData.error.unexist");
			else if (!this.miscellaneousDataService.findOne(miscellaneousDataId).getCurricula().getHacker().equals(b))
				redirectAttrs.addFlashAttribute("message", "miscellaneousData.error.notFromActor");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final MiscellaneousDataForm miscellaneousDataForm, final BindingResult binding) {
		ModelAndView result;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		if (binding.hasErrors())
			result = this.editModelAndView(miscellaneousDataForm, "miscellaneousData.commit.error");
		else
			try {
				Assert.notNull(miscellaneousDataForm);
				final MiscellaneousData miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataForm.getId());
				Assert.isTrue(miscellaneousData.getCurricula().getHacker().equals(b));
				miscellaneousData.setText(miscellaneousDataForm.getText());
				miscellaneousData.setAttachments(miscellaneousDataForm.getAttachments());

				this.miscellaneousDataService.save(miscellaneousData);

				result = new ModelAndView("redirect:/curricula/hacker/listData.do?curriculaId=" + miscellaneousData.getCurricula().getId());
			} catch (final Throwable oops) {
				result = this.editModelAndView(miscellaneousDataForm, "miscellaneousData.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final MiscellaneousDataForm miscellaneousDataForm, final BindingResult binding) {
		ModelAndView result;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		if (binding.hasErrors())
			result = this.editModelAndView(miscellaneousDataForm, "miscellaneousData.commit.error");
		else
			try {
				Assert.notNull(miscellaneousDataForm);
				final MiscellaneousData miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataForm.getId());
				Assert.isTrue(miscellaneousData.getCurricula().getHacker().equals(b));

				this.miscellaneousDataService.delete(this.miscellaneousDataService.findOne(miscellaneousDataForm.getId()));

				result = new ModelAndView("redirect:/curricula/hacker/listData.do?curriculaId=" + miscellaneousData.getCurricula().getId());
			} catch (final Throwable oops) {

				result = this.editModelAndView(miscellaneousDataForm, "miscellaneousData.commit.error");
			}
		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int miscellaneousDataId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		MiscellaneousData miscellaneousData = null;
		final Hacker b = this.hackerService.findHackerByUseraccount(LoginService.getPrincipal());
		final Collection<Curricula> curriculas = this.curriculaService.findByHackerId(b.getId());
		try {
			miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);
			Assert.notNull(miscellaneousData);
			Assert.isTrue(miscellaneousData.getCurricula().getHacker().getId() == b.getId());

			final MiscellaneousDataForm miscellaneousDataForm = new MiscellaneousDataForm();
			miscellaneousDataForm.setId(miscellaneousData.getId());
			miscellaneousDataForm.setText(miscellaneousData.getText());
			miscellaneousDataForm.setAttachments(miscellaneousData.getAttachments());

			result = this.ShowModelAndView(miscellaneousDataForm);
			result.addObject("curriculas", curriculas);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/miscellaneousData/hacker/list.do");
			if (this.miscellaneousDataService.findOne(miscellaneousDataId) == null)
				redirectAttrs.addFlashAttribute("message", "miscellaneousData.error.unexist");
			else if (!this.miscellaneousDataService.findOne(miscellaneousDataId).getCurricula().getHacker().equals(b))
				redirectAttrs.addFlashAttribute("message", "miscellaneousData.error.notFromActor");
		}
		return result;
	}

	// MODEL
	protected ModelAndView createModelAndView(final MiscellaneousDataForm miscellaneousDataForm) {
		ModelAndView result;
		result = this.createModelAndView(miscellaneousDataForm, null);
		return result;
	}

	protected ModelAndView createModelAndView(final MiscellaneousDataForm miscellaneousDataForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("miscellaneousData/create");

		result.addObject("message1", message);
		//result.addObject("requestURI", "miscellaneousData/hacker/create.do?curriculaId=" + miscellaneousDataForm.getCurricula().getId());
		result.addObject("requestURI", "miscellaneousData/hacker/create.do");
		result.addObject("miscellaneousDataForm", miscellaneousDataForm);
		result.addObject("isRead", false);
		result.addObject("id", 0);

		//result.addObject("curriculaId", miscellaneousDataForm.getCurricula().getId());
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView editModelAndView(final MiscellaneousDataForm miscellaneousDataForm) {
		ModelAndView result;
		result = this.editModelAndView(miscellaneousDataForm, null);
		return result;
	}

	protected ModelAndView editModelAndView(final MiscellaneousDataForm miscellaneousDataForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("miscellaneousData/edit");

		result.addObject("message", message);
		result.addObject("requestURI", "miscellaneousData/hacker/edit.do?miscellaneousDataId=" + miscellaneousDataForm.getId());
		result.addObject("miscellaneousDataForm", miscellaneousDataForm);
		result.addObject("isRead", false);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView ShowModelAndView(final MiscellaneousDataForm miscellaneousDataForm) {
		ModelAndView result;
		result = this.ShowModelAndView(miscellaneousDataForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final MiscellaneousDataForm miscellaneousDataForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("miscellaneousData/show");
		result.addObject("message", message);
		result.addObject("requestURI", "miscellaneousData/hacker/show.do?miscellaneousDataId=" + miscellaneousDataForm.getId());
		result.addObject("miscellaneousDataForm", miscellaneousDataForm);
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}
}
