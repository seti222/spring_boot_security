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
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UrlPathHelper;

import pe.seti222.service.menu.MenuInfoService;

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
		/*
		
		//String url = new UrlPathHelper().getPathWithinApplication(request);
		UrlPathHelper pathHelper = new UrlPathHelper();
       
		LOGGER.debug("getContextPath \t= ["+pathHelper.getContextPath(request)+"]");
		LOGGER.debug("getLookupPathForRequest \t= ["+pathHelper.getLookupPathForRequest(request)+"]");
		LOGGER.debug("getOriginatingContextPath \t= ["+pathHelper.getOriginatingContextPath(request)+"]");
		LOGGER.debug("getOriginatingQueryString \t= ["+pathHelper.getOriginatingQueryString(request)+"]");
		LOGGER.debug("getOriginatingRequestUri \t= ["+pathHelper.getOriginatingRequestUri(request)+"]");
		LOGGER.debug("getOriginatingServletPath \t= ["+pathHelper.getOriginatingServletPath(request)+"]");
		
		LOGGER.debug("getPathWithinApplication \t= ["+pathHelper.getPathWithinApplication(request)+"]");
		LOGGER.debug("getPathWithinServletMapping \t= ["+pathHelper.getPathWithinServletMapping(request)+"]");
		LOGGER.debug("getRequestUri \t= ["+pathHelper.getRequestUri(request)+"]");
		LOGGER.debug("getServletPath \t= ["+pathHelper.getServletPath(request)+"]");
		LOGGER.debug("decodeRequestString \t= ["+pathHelper.decodeRequestString(request, request.getRequestURI())+"]");

		LOGGER.debug("dddddd =>"+(String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
		*/
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
