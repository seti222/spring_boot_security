package pe.seti222.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.seti222.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);
}
