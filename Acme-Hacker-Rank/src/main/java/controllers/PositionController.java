
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import services.PositionService;
import domain.Position;
import forms.SearchForm;

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

		result.addObject("searchForm", new SearchForm());
		result.addObject("requestURI", "position/list.do");
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SearchForm searchForm, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = new ModelAndView("redirect:/welcome/index.do");
		else
			try {

				final Collection<Position> positions = this.positionService.search(searchForm.getKeyword());

				result = new ModelAndView("position/list");

				result.addObject("searchForm", searchForm);
				result.addObject("positions", positions);
				result.addObject("requestURI", "position/list.do");
				result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
				result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

			} catch (final Throwable oops) {

				result = new ModelAndView("redirect:/welcome/index.do");
				result.addObject("message", "message.commit.error");

			}
		return result;
	}

}
