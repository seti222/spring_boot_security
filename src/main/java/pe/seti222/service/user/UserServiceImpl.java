package pe.seti222.service.user;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.seti222.domain.User;
import pe.seti222.domain.UserCreateForm;
import pe.seti222.repo.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByUserId(String userId) {
        LOGGER.debug("Getting user by userId={}", userId);
        return userRepository.findOneByUserId(userId);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll(new Sort("userId"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setUserId(form.getUserId());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        
        user.setRole(form.getRole());
        return userRepository.save(user);
    }

}
