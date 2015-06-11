package pe.seti222.config.filter;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import pe.seti222.service.menu.MenuInfoService;
import pe.seti222.service.menu.MenuRoleService;

/**
 * DB 기반의 인증 관리 시스템.
 */
public class FmsFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger LOGGER = LoggerFactory.getLogger(FmsFilterInvocationSecurityMetadataSource.class);

	private final MenuInfoService menuInfoService;
	private final FmsUrlParser parser;
	private final Map<String, String[]> permissions;

	public FmsFilterInvocationSecurityMetadataSource(MenuInfoService menuRoleService) {
		this.menuInfoService = menuRoleService;
		parser = new FmsUrlParser();
		permissions = new Hashtable<>();
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		final String httpMethod = request.getMethod().toUpperCase();
		final String url = parser.parse(request.getRequestURI());
		final String key = url;//String.format("%s %s", httpMethod, url);
		LOGGER.debug("getAttributes url = > " + url);
		String[] permission ={"ROLE_ANONYMOUS"} ;
		//TODO 추후 설정값에서 가져오도록 변경 필요. 
		if (url.startsWith("/admin")) {
			if (permissions.containsKey(key)) {
				permission = permissions.get(key);
			} else {
				//permission = menuInfoService.findByMethodAndUrl(httpMethod, url);
				permission = menuInfoService.getRoleByUrl(url);
				if (permission != null) {
					permissions.put(key, permission);
				}
			}
		}else {
			if (permissions.containsKey(key)) {
				permission = permissions.get(key);
			} else {
				permissions.put(key, permission);
			}

		}

		String[] roles;
		if (permission == null) {
			roles = new String[] { "ROLE_ANONYMOUS" };
		} else {
			roles = permission;//new String[] { permission.getName() };
		}

		return SecurityConfig.createList(roles);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	/**
	 * url - permission hashmap clear
	 */
	public void clear() {
		permissions.clear();
	}
}
