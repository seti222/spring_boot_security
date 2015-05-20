package pe.seti222.service.user;

import java.util.Collection;

import pe.seti222.domain.User;
import pe.seti222.domain.UserCreateForm;

public interface UserService {

    User getUserById(long id);

    User getUserByUserId(String userId);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
