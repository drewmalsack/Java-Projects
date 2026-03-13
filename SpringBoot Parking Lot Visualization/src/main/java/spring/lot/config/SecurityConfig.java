package spring.lot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("password").roles("USER","ADMIN").build());
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            //.authorizeHttpRequests((req) -> req.requestMatchers(HttpMethod.GET, "/").hasAnyRole("USER", "ADMIN"))
            //.authorizeHttpRequests((req) -> req.requestMatchers(HttpMethod.POST, "/").hasRole("ADMIN"));
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/lot-view", "/search", "/css/**", "/js/**").permitAll() // Everyone can see
                .requestMatchers("/park", "/vacate", "/add-space", "/delete-space").hasRole("ADMIN") // Only Admins can edit
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .defaultSuccessUrl("/lot-view", true)
                .permitAll()
            )
            .logout(logout -> logout.permitAll());
        return http.build();
    }
}
