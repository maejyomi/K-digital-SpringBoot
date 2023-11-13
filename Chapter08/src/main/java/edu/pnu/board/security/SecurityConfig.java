package edu.pnu.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Autowired
	private BoardOAuth2UserDetailsService oauthService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterCahin(HttpSecurity security) throws Exception {
		security.userDetailsService(userDetailsService);
		
		security.authorizeHttpRequests(auth->auth
				.requestMatchers(new AntPathRequestMatcher("/board/**")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
				.anyRequest().permitAll());
		
		security.csrf(csrf->csrf.disable());
		security.formLogin(frmLogin->frmLogin.loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true));
		
		security.exceptionHandling(ex -> ex.accessDeniedPage("/system/accessDenied"));
		security.logout(logout->logout.logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/"));
		
		security.oauth2Login(oauth2->{
			oauth2.loginPage("/login")
				.userInfoEndpoint(uend->uend.userService(oauthService))
				.defaultSuccessUrl("/", true);
		});
		
		return security.build();
	}


}
