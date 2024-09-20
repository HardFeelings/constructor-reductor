package ru.vpt.constructorapp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.vpt.constructorapp.service.commercial.EmployeeService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private EmployeeService employeeService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/security/**").authenticated()
//                        .requestMatchers("/security/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/security/motorAdapterType/**").hasRole("ADMIN")
//                        .requestMatchers("/security/motor**").hasRole("ADMIN")
//                        .requestMatchers("/security/motorType**").hasRole("ADMIN")
//                        .requestMatchers("/security/product**").hasRole("ADMIN")
//                        .requestMatchers("/security/productOption**").hasRole("ADMIN")
//                        .requestMatchers("/security/productType**").hasRole("ADMIN")
//                        .requestMatchers("/security/reducer**").hasRole("ADMIN")
//                        .requestMatchers("/security/reducerInputType**").hasRole("ADMIN")
//                        .requestMatchers("/security/reducerInstallationType**").hasRole("ADMIN")
//                        .requestMatchers("/security/reducerMounting**").hasRole("ADMIN")
//                        .requestMatchers("/security/reducerOutputShaftType**").hasRole("ADMIN")
//                        .requestMatchers("/security/reducerSize**").hasRole("ADMIN")
//                        .requestMatchers("/security/reducerType**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(employeeService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
