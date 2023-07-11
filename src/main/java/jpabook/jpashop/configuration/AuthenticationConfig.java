package jpabook.jpashop.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configurable
@EnableWebSecurity
public class AuthenticationConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()  // 토큰으로 할 것이기에 disable() 으로 함.
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/api/v1/members", "/api/v1/login").permitAll() // join, login은 언제나 가능
                .antMatchers(HttpMethod.GET, "/api/**").permitAll() // 모든 get요청 허용
                .antMatchers(HttpMethod.POST, "/api/**").authenticated() // 모든 post요청을 인증된사용자인지 check
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt사용하는 경우 씀
                .and()
                .build();
                /*.and()
                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class) //UserNamePasswordAuthenticationFilter적용하기 전에 JWTTokenFilter를 적용 하라는 뜻 입니다.
                .build();*/
    }

}
