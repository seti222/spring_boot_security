package pe.seti222.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.seti222.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findOneByUserId(String userId);
}
