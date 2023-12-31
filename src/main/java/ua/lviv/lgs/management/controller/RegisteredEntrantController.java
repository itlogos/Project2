package ua.lviv.lgs.management.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.management.domain.Faculty;
import ua.lviv.lgs.management.domain.RegisteredEntrant;
import ua.lviv.lgs.management.domain.User;
import ua.lviv.lgs.management.service.FacultyService;
import ua.lviv.lgs.management.service.RegisteredEntrantService;
import ua.lviv.lgs.management.service.UserService;

public class RegisteredEntrantController {

	@Autowired
	private UserService userService;

	@Autowired
	FacultyService facultyService;

	@Autowired
	private RegisteredEntrantService registeredEntrantService;

	@RequestMapping(value = "/entrantRegistration", method = RequestMethod.GET)
	public ModelAndView showSubjects(@RequestParam("currentFacultyId") Integer currentFaculty,
			@RequestParam("currentUserEmail") String currentUserEmail) {
		Faculty faculty = facultyService.findFacultyById(currentFaculty);
		User user = userService.findByEmail(currentUserEmail);

		RegisteredEntrant registeredEntrant = new RegisteredEntrant();
		registeredEntrant.setFaculty(faculty);
		registeredEntrant.setUser(user);

		ModelAndView modelAndView = new ModelAndView("entrantRegistration");
		modelAndView.addObject("registeredEntrant", registeredEntrant);
		return modelAndView;
	}

	@RequestMapping(value = "/addMarks", method = RequestMethod.POST)
	public String registration(@RequestParam MultipartFile image, @RequestParam List<Double> marks,
			@RequestParam Integer facultyId, @RequestParam String userEmail) throws IOException {
		Faculty faculty = facultyService.findFacultyById(facultyId);
		User user = userService.findByEmail(userEmail);

		RegisteredEntrant registeredEntrant = new RegisteredEntrant(user, faculty, marks);
		registeredEntrant.setEncodedEntrantImage(Base64.getEncoder().encodeToString(image.getBytes()));

		registeredEntrantService.save(registeredEntrant);
		return "redirect:/home";
	}

	@RequestMapping(value = "/registeredEntrants", method = RequestMethod.GET)
	public ModelAndView showRating() {
		ModelAndView modelAndView = new ModelAndView("registeredEntrants");
		modelAndView.addObject("registeredEntrants", registeredEntrantService.findAllRegisteredEntrants());
		return modelAndView;
	}
}
