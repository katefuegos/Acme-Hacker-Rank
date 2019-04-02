
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import services.PositionService;
import domain.Position;

@Controller
@RequestMapping("/position")
public class PositionController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private PositionService			positionService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor---------------------------------------

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Position> positions = this.positionService.findFinalMode();
		result = new ModelAndView("position/list");
		result.addObject("positions", positions);

		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}
	//
	//	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final SearchForm searchForm, final BindingResult binding) {
	//
	//		ModelAndView result;
	//
	//		if (binding.hasErrors())
	//			result = this.createSearch(actorForm);
	//		else
	//			try {
	//
	//				Assert.isTrue(actorForm.getCheckTerms(), "actor.check.true");
	//				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	//				actorForm.getUserAccount().setPassword(encoder.encodePassword(actorForm.getUserAccount().getPassword(), null));
	//				this.actorService.update(actorForm);
	//
	//				result = new ModelAndView("redirect:/welcome/index.do");
	//			} catch (final Throwable oops) {
	//				final Actor test = this.actorService.findActorByUsername(actorForm.getUserAccount().getUsername());
	//
	//				if (test != null)
	//					result = this.createEditModelAndView(actorForm, "actor.userExists");
	//				else if (oops.getMessage() == "actor.check.true")
	//					result = this.createEditModelAndView(actorForm, oops.getMessage());
	//				else if (oops.getMessage() == "actor.creditcard.error.date.invalid")
	//					result = this.createEditModelAndView(actorForm, oops.getMessage());
	//
	//				else
	//					result = this.createEditModelAndView(actorForm, "message.commit.error");
	//
	//			}
	//		return result;
	//	}
	//
	//	// CreateModelAndView
	//	protected ModelAndView createSearch(final SearchForm actorForm) {
	//		ModelAndView result;
	//
	//		result = this.createEditModelAndView(actorForm, null);
	//
	//		return result;
	//
	//	}
	//
	//	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final String message) {
	//		ModelAndView result = null;
	//
	//		// TODO faltan actores
	//		final Collection<Authority> authorities = actorForm.getUserAccount().getAuthorities();
	//		final Authority company = new Authority();
	//		company.setAuthority("COMPANY");
	//		final Authority hacker = new Authority();
	//		hacker.setAuthority("HACKER");
	//
	//		if (authorities.contains(company))
	//			result = new ModelAndView("register/company");
	//		else if (authorities.contains(hacker))
	//			result = new ModelAndView("register/hacker");
	//		else
	//			throw new NullPointerException();
	//
	//		result.addObject("actorForm", actorForm);
	//
	//		result.addObject("message", message);
	//		result.addObject("isRead", false);
	//
	//		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
	//		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
	//		return result;
	//	}

}
