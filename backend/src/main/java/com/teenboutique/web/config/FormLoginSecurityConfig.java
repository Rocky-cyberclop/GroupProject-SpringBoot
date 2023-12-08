package com.teenboutique.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.teenboutique.web.entities.Role;
import com.teenboutique.web.repositories.RoleRepository;
import com.teenboutique.web.services.JwtAuthentication;
import com.teenboutique.web.services.LoginEntityDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class FormLoginSecurityConfig {
	@Autowired
	private LoginEntityDetailServiceImpl loginEntityDetailServiceImpl;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JwtAuthentication jwtAuthentication;

	public String[] allRole() {
		String[] roleStrings = new String[roleRepository.findAll().size()];
		int i = 0;
		for (Role role : roleRepository.findAll()) {
			roleStrings[i] = role.getName();
			i++;
		}
		return roleStrings;
	}

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(loginEntityDetailServiceImpl);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	@Order(1)
	public SecurityFilterChain filterChainApis(HttpSecurity http) throws Exception {
		http.securityMatcher(AntPathRequestMatcher.antMatcher("/api/**"))
			.authorizeHttpRequests(req -> 
				req.requestMatchers("/api/admin/auth/**", "/api/login/**").permitAll()
						.anyRequest()
						.authenticated()
			)
			.cors(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(daoAuthenticationProvider())
			.addFilterBefore(jwtAuthentication, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}


	@Bean
	@Order(2)
	SecurityFilterChain filterChainAdmin(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.securityMatcher(AntPathRequestMatcher.antMatcher("/admin/**"))
				.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
						.requestMatchers("/admin/css/**", "/admin/images/**", "/admin/fonts/**", "/admin/js/**", "/admin/scss/**", "/admin/vendors/**").permitAll()
						.requestMatchers("/uploads/**").permitAll()
						.requestMatchers("/admin/login").permitAll()
						.requestMatchers("/admin/management/**").hasAuthority("Admin")
						.requestMatchers("/admin/**").hasAnyAuthority(allRole())
						.anyRequest().authenticated())
				.formLogin((formLogin) -> formLogin
						.usernameParameter("id")
						.passwordParameter("pass")
						.loginPage("/admin/login")
						.defaultSuccessUrl("/admin").permitAll()
						.failureUrl("/admin/login?error=true").permitAll())
				.logout((logout) -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
						.logoutSuccessUrl("/admin/login").permitAll());
		httpSecurity.authenticationProvider(daoAuthenticationProvider());
		return httpSecurity.build();
	}

	@Bean
	@Order(3)
	SecurityFilterChain filterChainUser(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
						.requestMatchers("/main/css/**", "/main/images/**", "/main/js/**", "/main/plugins/**").permitAll()
						.requestMatchers("/uploads/**").permitAll()
						.requestMatchers("/", "/login", "/forget", "/register").permitAll()
						.requestMatchers("/main/**").hasAuthority("User")
						.anyRequest().authenticated())
				.formLogin((formLogin) -> formLogin
						.usernameParameter("email")
						.passwordParameter("password")
						.loginPage("/login")
						.defaultSuccessUrl("/").permitAll()
						.failureUrl("/login?error=true").permitAll())
				.logout((logout) -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/").permitAll());
		httpSecurity.authenticationProvider(daoAuthenticationProvider());
		return httpSecurity.build();
	}

}
