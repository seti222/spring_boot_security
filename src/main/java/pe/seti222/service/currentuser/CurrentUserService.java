package pe.seti222.service.currentuser;

import pe.seti222.domain.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
