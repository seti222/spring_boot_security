package pe.seti222.config.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import pe.seti222.config.ApiController;

public class RoleFilter extends GenericFilterBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleFilter.class);

    
    
    private AuthenticationManager authenticationManager;
    private Set<String> managementEndpoints;
 
 
    public RoleFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        prepareManagementEndpointsSet(); 
    } 
 
 
    private void prepareManagementEndpointsSet() { 
        managementEndpoints = new HashSet<>();
        managementEndpoints.add(ApiController.AUTOCONFIG_ENDPOINT);
        managementEndpoints.add(ApiController.BEANS_ENDPOINT);
        managementEndpoints.add(ApiController.CONFIGPROPS_ENDPOINT);
        managementEndpoints.add(ApiController.ENV_ENDPOINT);
        managementEndpoints.add(ApiController.MAPPINGS_ENDPOINT);
        managementEndpoints.add(ApiController.METRICS_ENDPOINT);
        managementEndpoints.add(ApiController.SHUTDOWN_ENDPOINT);
    } 
    
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = asHttp(request);
		HttpServletResponse httpResponse = asHttp(response);
        String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
        //authenticationManager.authenticate(authentication)
        String username = httpRequest.getHeader("X-Auth-Username");
        String password = httpRequest.getHeader("X-Auth-Password");
        String token = httpRequest.getHeader("X-Auth-Token");
        
        LOGGER.debug("RoleFilter url=> "+resourcePath);
        LOGGER.debug("RoleFilter username=> "+username);
        LOGGER.debug("RoleFilter password=> "+password);
        LOGGER.debug("RoleFilter token=> "+token);

        
        chain.doFilter(request, response);
        //SecurityContextHolder.clearContext(); 
        //httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "test!!!");
	}

	private HttpServletRequest asHttp(ServletRequest request) {
		return (HttpServletRequest) request;
	}

	private HttpServletResponse asHttp(ServletResponse response) {
		return (HttpServletResponse) response;
	}
}
