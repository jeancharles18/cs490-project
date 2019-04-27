package mum.pmp.mstore.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import mum.pmp.mstore.service.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static String REALM = "mStore";

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("In configure webSecurityConfig");

		http.authorizeRequests()
				.antMatchers("/webjars/**", "/css/**", "/images/**", "/signup", "/login", "/category").permitAll()
				.and()
				.httpBasic()
				.realmName(REALM)
				.authenticationEntryPoint(getBasicAuthEntryPoint());
		
	//	http.csrf().disable();
		http.headers().frameOptions().disable();
		
//		http
//	      .authorizeRequests()
//	          .antMatchers("/", "/home", "/index").permitAll()
//	          .anyRequest().authenticated()
//	          .and()
//	      .formLogin()
//	      	.loginPage("/login")
//	      	.permitAll()
//	      	.and()
//	      .logout()
//	      	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	      	.logoutSuccessUrl("/")
//	        .permitAll();

		
		http.authorizeRequests()
        .anyRequest()//allow all urls
        .authenticated()//all URLs are allowed by any authenticated user, no role restrictions.
        .and()
        .formLogin()//enable form based authentication
        .loginPage("/login")//use a custom login URI
        .permitAll(true)//login URI can be accessed by anyone
        .and()
        .logout()//default logout handling
        .logoutSuccessUrl("/my-login?logout")//our new logout success url, we are not replacing other defaults.
        .permitAll();//allow all as it will be accessed when user is not logged in anymore

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		System.out.println("Get Basic Auth Entry point");
		return new CustomBasicAuthenticationEntryPoint();
	}
}