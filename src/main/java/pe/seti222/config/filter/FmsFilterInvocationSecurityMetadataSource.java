package pe.seti222.config.filter;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import pe.seti222.service.menu.MenuRoleService;

/**
 * DB 기반의 인증 관리 시스템.
 */
public class FmsFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger LOGGER = LoggerFactory.getLogger(FmsFilterInvocationSecurityMetadataSource.class);

	private final MenuRoleService menuRoleService;
	private final FmsUrlParser parser;
	private final Map<String, Permission> permissions;

	public FmsFilterInvocationSecurityMetadataSource(MenuRoleService menuRoleService) {
		this.menuRoleService = menuRoleService;
		parser = new FmsUrlParser();
		permissions = new Hashtable<>();
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		final String httpMethod = request.getMethod().toUpperCase();
		final String url = parser.parse(request.getRequestURI());
		final String key = String.format("%s %s", httpMethod, url);
		LOGGER.debug("getAttributes url = > " + url);
		Permission permission = null;
		if (url.startsWith("/user")) {
			if (permissions.containsKey(key)) {
				permission = permissions.get(key);
			} else {
				permission = menuRoleService.findByMethodAndUrl(httpMethod, url);
				if (permission != null) {
					permissions.put(key, permission);
				}
			}
		}else {
			if (permissions.containsKey(key)) {
				permission = permissions.get(key);
			} else {
				permission = new Permission("ROLE_ANONYMOUS");
				if (permission != null) {
					permissions.put(key, permission);
				}
			}

		}

		String[] roles;
		if (permission == null) {
			roles = new String[] { "ROLE_ANONYMOUS" };
		} else {
			roles = new String[] { permission.getName() };
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
