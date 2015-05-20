package pe.seti222.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.seti222.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUserId(String userId);
}
