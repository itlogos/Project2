package ua.lviv.lgs.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.management.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
