package pe.seti222.domain.menu;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "role_info")
public class RoleInfo {
	@Id
	@Column
	String roleCode;
	@Column
	String roleName;
	
	@ManyToMany(mappedBy="roleList")
    private List<MenuInfo> menuList;

	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public List<MenuInfo> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuInfo> menuList) {
		this.menuList = menuList;
	}
	@Override
	public String toString() {
		return "RoleInfo [roleCode=" + roleCode + ", roleName=" + roleName + "]";
	}
	
	
}
