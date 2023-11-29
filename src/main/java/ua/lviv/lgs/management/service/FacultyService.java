package ua.lviv.lgs.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.management.dao.FacultyRepository;
import ua.lviv.lgs.management.domain.Faculty;

@Service
public class FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;

	public Faculty save(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	public List<Faculty> findAllFaculties() {
		return facultyRepository.findAll();
	}
	
	public Faculty findFacultyById(Integer id) {
		return facultyRepository.findById(id).get();
	}

}
