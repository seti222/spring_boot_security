package pe.seti222.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import pe.seti222.config.filter.RoleFilter;
import pe.seti222.service.menu.MenuRoleService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private MenuRoleService menuRoleService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().antMatchers("/", "/public/**").permitAll()
			.antMatchers("/users/**").hasAuthority("ADMIN").anyRequest().fullyAuthenticated()
			.and().formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("userId").permitAll()
			.and().logout().logoutUrl("/logout")
			//.deleteCookies("remember-me")
			.logoutSuccessUrl("/").permitAll()
			//.and().rememberMe()
			.and().authorizeRequests().anyRequest().authenticated()
			//.and().addFilterAfter(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
			;
		
		http.addFilterAfter(new RoleFilter(authenticationManager()), BasicAuthenticationFilter.class);
	}
	

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * * FMS API 권한 Filter. * @return securityMetadataSource() 가 적용된 데이터. * @throws
	 * Exception
	 *//*
	@Bean
	public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource());
		filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
		filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
		
		
		return filterSecurityInterceptor;
	}

	// 여러 Voter를 추가하는 것이 가능. List로 index가 작은 Voter가 먼저 투표. 투표
	// 포기(ACCESS_ABSTAIN) //가 되는 경우, 다음 Voter에게 넘어감.

	@Bean
	public AffirmativeBased affirmativeBased() {
		DBAccessDecisionVoter voter = new DBAccessDecisionVoter();
		List<AccessDecisionVoter> voters = new ArrayList<>();
		voters.add(voter);
		return new AffirmativeBased(voters);
	}

	@Bean
	public FilterInvocationSecurityMetadataSource securityMetadataSource() {
		return new FmsFilterInvocationSecurityMetadataSource(menuRoleService);
	}*/

}