package com.cdh.apitaller.repository;

import com.cdh.apitaller.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByDni(String dni);
    User findByNombreUsuarioApp(String s);
}
