package pe.seti222.config.filter;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

public class DBAccessDecisionVoter implements
		AccessDecisionVoter<FilterInvocation> {

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
	public int vote(Authentication authentication, FilterInvocation fi,
			Collection<ConfigAttribute> attributes) {
		assert authentication != null;
		assert fi != null;
		assert attributes != null;
		SecurityConfig securityConfig = null;
		boolean containAuthority = false;
		for (final ConfigAttribute configAttribute : attributes) {
			if (configAttribute instanceof SecurityConfig) {
				securityConfig = (SecurityConfig) configAttribute;
				for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
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
		return containAuthority ? ACCESS_GRANTED : ACCESS_DENIED;
	}

}
