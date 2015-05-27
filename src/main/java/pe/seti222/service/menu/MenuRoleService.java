package pe.seti222.service.menu;

import org.springframework.stereotype.Service;

import pe.seti222.config.filter.Permission;

@Service
public class MenuRoleService {

	public Permission findByMethodAndUrl(String httpMethod, String url) {
		// TODO Auto-generated method stub
		if(url.indexOf("/login") > -1){
			return new Permission("ROLE_ANONYMOUS"); 
		}
		return null;
	}

}
