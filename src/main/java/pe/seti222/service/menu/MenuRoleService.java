package pe.seti222.service.menu;

import org.springframework.stereotype.Service;

import pe.seti222.config.filter.Permission;

@Service
public class MenuRoleService {

	public Permission findByMethodAndUrl(String httpMethod, String url) {
		// TODO Auto-generated method stub
		if(url.indexOf("/login") > -1){
			return new Permission("ROLE_ANONYMOUS"); 
		}else if(url.indexOf("/") > -1){
			return new Permission("ROLE_ANONYMOUS"); 
		}else if(url.indexOf("/user") > -1){
			return new Permission("R_ADMIN"); 
		}else if(url.indexOf("favicon.ico")> -1) {
			return new Permission("ROLE_ANONYMOUS");
		}else {
			return new Permission("ROLE_ANONYMOUS");
		}
	}

}
