package ua.lviv.lgs.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.management.domain.RegisteredEntrant;

public interface RegisteredEntrantRepository extends JpaRepository<RegisteredEntrant, Integer>{

}
