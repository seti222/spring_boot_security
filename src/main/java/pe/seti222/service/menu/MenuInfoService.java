package pe.seti222.service.menu;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.seti222.config.filter.Permission;
import pe.seti222.domain.menu.MenuInfo;
import pe.seti222.domain.menu.RoleInfo;
import pe.seti222.repo.menu.MenuInfoRepository;

@Service
public class MenuInfoService {
	@Autowired
	private MenuInfoRepository menuInfoRepo ;
	
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

	public List<MenuInfo> findAll(){
		List<MenuInfo> menuList = menuInfoRepo.findAll();
		if(menuList == null ){
			menuList = new ArrayList<MenuInfo>();
		}
		return menuList;
	}

	@Transactional
	public String[] getRoleByUrl(String url) {
		String [] result = {};
		// TODO Auto-generated method stub
		
		List<MenuInfo> list = menuInfoRepo.findByMenuUrl(url);
		if(list != null && list.size()>0){
			List<RoleInfo> rlist = list.get(0).getRoleList();
			System.out.println(rlist);
			String [] rdata = new String[rlist.size()];
			for(int i=0;i<rlist.size();i++){
				rdata[i] = rlist.get(i).getRoleCode();
			}
			return  rdata;
		}
		return result;
	}
}
