package ua.lviv.lgs.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.management.domain.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

}
