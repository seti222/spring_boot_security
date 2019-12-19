package pe.seti222.repo.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.seti222.domain.menu.MenuInfo;

public interface MenuInfoRepository extends JpaRepository<MenuInfo, String> {
	
	public List<MenuInfo> findByMenuUrl(String url);

}
