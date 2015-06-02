package pe.seti222.config.filter;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

public class DBAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBAccessDecisionVoter.class);

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return attribute instanceof SecurityConfig;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz != null && clazz.isAssignableFrom(FilterInvocation.class);
	}

	@Override
	public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
		assert authentication != null;
		assert fi != null;
		assert attributes != null;
		SecurityConfig securityConfig = null;
		boolean containAuthority = false;
		boolean isData = true;
		LOGGER.debug("vote [" + attributes + "]");
		String roleName = "";
		for (final ConfigAttribute configAttribute : attributes) {

			if (configAttribute instanceof SecurityConfig) {
				securityConfig = (SecurityConfig) configAttribute;
				for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
					LOGGER.debug(grantedAuthority.getAuthority() + "" + "");
					containAuthority = securityConfig.getAttribute().equals(grantedAuthority.getAuthority());
					if (containAuthority) {
						break;
					}
				}
				if (containAuthority) {
					break;
				}
			}
		}
		/*if(containAuthority && roleName.equals("ROLE_ANONYMOUS")){
			return ACCESS_ABSTAIN;
		}*/
		return containAuthority ? ACCESS_GRANTED : ACCESS_DENIED;
	}

}
