package pe.seti222.domain.menu;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "menu_Info")
public class MenuInfo {
	@Id
	@Column
	String menuCode;
	
	@Column
	String menuName;
	
	@Column
	String menuUrl;
	
	@Column(name="pmenu_code")
	String parentMenuCode;
	
	@Column
	int menuDepth;
	
	@Column(name="s_order")
	int displayOrder;

	@ManyToMany
	@JoinTable(name = "menu_role", 
		joinColumns = { @JoinColumn(name = "menu_code") }, 
		inverseJoinColumns = { @JoinColumn(name = "role_code") })
	private List<RoleInfo> roleList;
	
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getParentMenuCode() {
		return parentMenuCode;
	}

	public void setParentMenuCode(String parentMenuCode) {
		this.parentMenuCode = parentMenuCode;
	}

	public int getMenuDepth() {
		return menuDepth;
	}

	public void setMenuDepth(int menuDepth) {
		this.menuDepth = menuDepth;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}


	public List<RoleInfo> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleInfo> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "MenuInfo [ menuCode=" + menuCode + ", menuName=" + menuName + ", menuUrl=" + menuUrl + ", parentMenuCode="
				+ parentMenuCode + ", menuDepth=" + menuDepth + ", displayOrder=" + displayOrder + ", roleList=" + roleList + "]";
	}

	
}
