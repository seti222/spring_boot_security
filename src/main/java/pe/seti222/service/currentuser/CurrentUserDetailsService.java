package pe.seti222.service.currentuser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.seti222.domain.CurrentUser;
import pe.seti222.domain.User;
import pe.seti222.service.user.UserService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    
    @Override
    public CurrentUser loadUserByUsername(String userId) throws UsernameNotFoundException {
        LOGGER.debug("Authenticating user with userId={}", userId);
        //User user = userService.(userId).orElseThrow(() -> new UsernameNotFoundException(String.format("User with userId=%s was not found", userId)));
        return null;//new CurrentUser(user);
    }

}
