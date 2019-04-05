
package controllers.Hacker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
import domain.MiscellaneousData;
import domain.PositionData;

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
			result = new ModelAndView("curricula/list");
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

}
