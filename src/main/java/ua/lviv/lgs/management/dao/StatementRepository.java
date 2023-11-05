package ua.lviv.lgs.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.management.domain.Statement;

public interface StatementRepository extends JpaRepository<Statement, Integer> {

}
