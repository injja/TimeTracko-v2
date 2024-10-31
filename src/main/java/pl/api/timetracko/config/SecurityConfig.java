package pl.api.timetracko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register").permitAll() // Allow access to /register without authentication
                        .anyRequest().authenticated()         // Secure all other endpoints
                )
                .csrf(customizer -> customizer.disable())
                .formLogin(form -> form.defaultSuccessUrl("/workspace", true).permitAll())
                .httpBasic(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .build();
//               .sessionManagement(customizer -> customizer.sessionCreationPolicy(Sess))


    }
    //        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/register", "/login", "/project","/css/**", "/js/**", "/images/**").permitAll()  // Pozwól na dostęp do stron rejestracji i logowania
//                        .anyRequest().authenticated()  // Wymagaj autoryzacji do wszystkich innych zasobów
//                )
//                .formLogin(form -> form  // Konfiguracja logowania
//                        .loginPage("/login")  // Niestandardowa strona logowania
//                        .defaultSuccessUrl("/project", true)  // Strona, na którą przekierowuje po zalogowaniu
//                        .permitAll()
//                )
//                .logout(logout -> logout  // Konfiguracja wylogowania
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login")
//
//                )
//                .csrf(csrf -> csrf.disable());  // Wyłączenie CSRF (opcjonalnie, nie zawsze jest to dobry pomysł)
//
//        return http.build();
//    }
//
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}