package controllers.Hacker;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.ApplicationService;
import services.ConfigurationService;
import services.HackerService;
import controllers.AbstractController;
import domain.Application;

@Controller
@RequestMapping("/application/hacker")
public class ApplicationHackerController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private HackerService hackerService;

	@Autowired
	private ConfigurationService configurationService;

	// Constructor---------------------------------------------------------

	public ApplicationHackerController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			int hackerId = hackerService.findHackerByUseraccount(
					LoginService.getPrincipal()).getId();
			Assert.notNull(hackerService.findOne(hackerId));

			Collection<Application> applications = applicationService
					.findByHackerId(hackerId);

			Collection<Application> applicationsPending = new ArrayList<Application>();
			Collection<Application> applicationsRejected = new ArrayList<Application>();
			Collection<Application> applicationsAccepted = new ArrayList<Application>();
			Collection<Application> applicationsSubmitted = new ArrayList<Application>();

			for (Application a : applications) {
				if (a.getStatus().equals("PENDING")) {
					applicationsPending.add(a);
				} else if (a.getStatus().equals("ACCEPTED")) {
					applicationsAccepted.add(a);
				} else if (a.getStatus().equals("REJECTED")) {
					applicationsRejected.add(a);
				} else if (a.getStatus().equals("SUBMITTED")) {
					applicationsSubmitted.add(a);
				}
			}

			result = new ModelAndView("application/listHacker");
			result.addObject("applicationsPending", applicationsPending);
			result.addObject("applicationsRejected", applicationsRejected);
			result.addObject("applicationsAccepted", applicationsAccepted);
			result.addObject("applicationsSubmitted", applicationsSubmitted);
			result.addObject("requestURI", "application/hacker/list.do");
			result.addObject("banner", this.configurationService.findAll()
					.iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll()
					.iterator().next().getSystemName());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}
}
