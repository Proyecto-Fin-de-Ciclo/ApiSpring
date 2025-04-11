package com.cdh.apilibreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdh.apilibreria.model.Temas;
import com.cdh.apilibreria.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findById(int id);

}
