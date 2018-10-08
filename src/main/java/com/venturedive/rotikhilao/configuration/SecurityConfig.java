package com.venturedive.rotikhilao.configuration;

import com.venturedive.rotikhilao.filters.JwtTokenAuthenticationFilter;
import com.venturedive.rotikhilao.filters.OpenIdConnectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2RestTemplate restTemplate;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Bean
    public OpenIdConnectFilter myFilter() {
        final OpenIdConnectFilter filter = new OpenIdConnectFilter("/login");
        filter.setRestTemplate(restTemplate);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.cors().and().csrf().disable();
        ;

        // @formatter:on
//        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                // handle an authorized attempts
//                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                // Add a filter to validate the tokens with every request
//                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
//                // authorization requests config
//                .authorizeRequests()
//                // allow all who are accessing "auth" service
//                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
//                // must be an admin if trying to access admin area (authentication is also required here)
//                .antMatchers("/gallery" + "/admin/**").hasRole("ADMIN")
//                // Any other request must be authenticated
//                .anyRequest().authenticated();
    }
}