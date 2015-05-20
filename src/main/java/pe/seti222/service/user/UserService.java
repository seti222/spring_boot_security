package pe.seti222.service.user;

import java.util.Collection;
import java.util.Optional;

import pe.seti222.domain.User;
import pe.seti222.domain.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
