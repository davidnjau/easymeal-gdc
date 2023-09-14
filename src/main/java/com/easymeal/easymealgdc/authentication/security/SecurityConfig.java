package com.easymeal.easymealgdc.authentication.security;

import com.easymeal.easymealgdc.LoginResponse;
import com.easymeal.easymealgdc.authentication.entity.PersonDetails;
import com.easymeal.easymealgdc.authentication.entity.Role;
import com.easymeal.easymealgdc.authentication.filter.JwtTokenAuthenticationFilter;
import com.easymeal.easymealgdc.authentication.filter.JwtTokenFilter;
import com.easymeal.easymealgdc.authentication.filter.JwtTokenStore;
import com.easymeal.easymealgdc.authentication.service_class.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenStore jwtTokenStore;
    private final JwtTokenFilter jwtTokenFilter;
    private final PersonService personService;


    public SecurityConfig(JwtTokenStore jwtTokenStore, JwtTokenFilter jwtTokenFilter, PersonService personService) {
        this.jwtTokenStore = jwtTokenStore;
        this.jwtTokenFilter = jwtTokenFilter;
        this.personService = personService;
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.addFilterBefore( jwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class );
        http.addFilterBefore( jwtTokenFilter, JwtTokenAuthenticationFilter.class );
        http.csrf().disable().authorizeRequests()

                .antMatchers("/auth/api/v1/**").permitAll()
                .antMatchers("/login").permitAll()

                .antMatchers("/admin/api/v1/**").hasRole("ADMIN")

                .antMatchers(GET, "/swagger-ui/**").permitAll()
                .antMatchers(GET, "/swagger-ui.html").permitAll()
                .antMatchers(GET, "/download/**").permitAll()

                .antMatchers(GET, "/download-file/**").permitAll()
                .antMatchers(GET, "/upload/**").permitAll()
                .antMatchers(GET, "/actuator/**").permitAll()
                .antMatchers(GET, "/static/**").permitAll()
                .antMatchers(GET, "/assets/**").permitAll()

                .antMatchers("/login*").permitAll()



                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint( this::commence )
                .and()
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );

    }

    private void commence(HttpServletRequest request, HttpServletResponse response,
                          AuthenticationException authException) throws IOException, ServletException {

        response.setContentType( "application/json" );
        response.setStatus(403);
        response.getWriter().write( "{\"details\": \"User is not Authenticated\"}" );
    }

    @Bean
    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() throws Exception {
        JwtTokenAuthenticationFilter filter = new JwtTokenAuthenticationFilter();
        filter.setAuthenticationManager( authenticationManagerBean() );
        filter.setAuthenticationSuccessHandler(this::onAuthenticationSuccess  );
        filter.setAuthenticationFailureHandler( this::onAuthenticationFailure );
        filter.setFilterProcessesUrl("/auth/api/v1/login");
        return filter;
    }

    private void onAuthenticationSuccess(HttpServletRequest request,
                                         HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        try {
            String token = jwtTokenStore.generateToken( authentication );
            String emailAddress = authentication.getName();
            PersonDetails userDetails = personService
                    .getPersonByEmailAddress(emailAddress);
            if (userDetails != null){
                String userId = userDetails.getUserId();
                String name = userDetails.getPersonName();
                String phoneNumber = userDetails.getPhoneNumber();

                LoginResponse loginResponse = new LoginResponse(
                        token, userId, name, emailAddress, phoneNumber,

                        userDetails.getRolesCollection().stream()
                                .map(Role::getName)
                                .collect(Collectors.toList())

                );

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), loginResponse);

            }


        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private void onAuthenticationFailure(HttpServletRequest request,
                                         HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.setContentType( "application/json" );
        response.setStatus(400);
        response.getWriter().write( "{\"details\": \"" + exception.getMessage() +"\" }" );
    }
}
