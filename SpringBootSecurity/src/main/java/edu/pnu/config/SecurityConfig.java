package edu.pnu.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 이 클래스가 설정 클래스라고 정의 (loc 컨테이너에 로드)
@EnableWebSecurity // 스프링 시큐리티 적용에 필요한 객체를 자동 생성
public class SecurityConfig {
	@Autowired
	private DataSource dataSource;
	
	@Bean // 이 메서드가 리턴하는 객체를 loc 컨테이너에 등록하라는 지시
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(security->security
				.requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasRole("MANAGER")
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.csrf(cf->cf.disable());
		
		// 우리가 만든 로그인 페이지를 사용하고 싶을때 설정
		http.formLogin(form->form
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true) // true이면 로그인했을때 무조건 loginSuccess로 감
		);
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		
		http.logout(logout->logout
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/login"));
		
		return http.build();
	}

	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username, concat('{noop}', password) password, "
					+ "enable from member where username=?")
			.authoritiesByUsernameQuery("select username, role from member where username=?");
	}
	
	
//	@Autowired
//	public void authenticated(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("manager")
//			.password("{noop}abcd")
//			.roles("MANAGER");
//		
//		auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password("{noop}abcd")
//		.roles("ADMIN");
//	
//	}
}