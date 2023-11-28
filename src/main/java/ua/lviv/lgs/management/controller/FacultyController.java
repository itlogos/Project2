package ua.lviv.lgs.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.management.domain.Faculty;
import ua.lviv.lgs.management.service.FacultyService;

@Controller
public class FacultyController {

	@Autowired
	private FacultyService facultyService;

	@RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
	public ModelAndView createFaculty(@Validated @ModelAttribute("faculty") Faculty faculty,
			BindingResult bindingResult) {
		facultyService.save(faculty);
		return new ModelAndView("redirect:/home");
	}
}
