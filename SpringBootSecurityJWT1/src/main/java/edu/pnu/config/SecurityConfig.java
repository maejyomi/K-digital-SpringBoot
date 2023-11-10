package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MemberRepository memberRepository;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable()); //CSRF 보호 비활성화(JS에서 호출가능)
		
		http.authorizeHttpRequests(auth->auth
				.requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("MANAGER","ADMIN")
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.formLogin(frmLogin->frmLogin.disable()); // Form을 이용한 로그인을 사용하지 않겠다는 설정
		http.httpBasic(basic->basic.disable()); // Http Basic 인증 방식을 사용하지 않겠다는 설정
		
		// 세션을 유지하지 않겠다고 설정 -> Url 호출 뒤 응답할 때 까지는 유지되지만 응답 후 삭제된다는 의미.
		http.sessionManagement(ssmn->ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	
		// 스프링 시큐리티가 등록한 필터체인의 뒤에 작성한 필터를 추가한다.
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		// 스프링 시큐리티가 등록한 필터들 중에서 AuthorizationFilter 앞에서 작성한 필터를 삽입한다.
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		
		return http.build();
	}
	
	


}
