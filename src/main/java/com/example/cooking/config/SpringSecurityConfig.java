package com.example.cooking.config;

import com.example.cooking.buisness.enums.role.RoleEnum;
import com.example.cooking.presentation.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/ingredients/save", "/ingredients/update/{id}", "/ingredients/delete/{id}").hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/ingredients/{id}", "/ingredients").permitAll()
                .antMatchers("/dish-types/save", "/dish-types/update/{id}", "/dish-types/delete/{id}").hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/dish-types/{id}", "/dish-types/{id}/dish-names", "/dish-types").permitAll()
                .antMatchers("/dish-names/save", "/dish-names/update/{id}", "/dish-names/delete/{id}").hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/dish-names/{id}", "/dish-names/{id}/dishes", "/dish-names").permitAll()
                .antMatchers("/dishes/save", "/dishes/update/{id}", "/dishes/delete/{id}").hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/dishes/{id}", "/dishes").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/meal-times/save", "/meal-times/update/{id}", "/meal-times/delete/{id}").hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/meal-times/{id}", "/meal-times/{id}/dish-types", "/meal-times").permitAll()
                .antMatchers("/ingredient-kinds/save", "/ingredient-kinds/update/{id}", "/ingredient-kinds/delete/{id}").hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/ingredient-kinds/{id}", "/ingredient-kinds/{id}/ingredient-types", "/ingredient-kinds").permitAll()
                .antMatchers("/ingredient-types/save", "/ingredient-types/update/{id}", "/ingredient-types/delete/{id}").hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/ingredient-types/{id}", "/ingredient-types/{id}/ingredients", "/ingredient-types").permitAll()
                .antMatchers("/product-kinds/save", "/product-kinds/update/{id}", "/product-kinds/delete/{id}").hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/product-kinds/{id}", "/product-kinds/{id}/ingredient-kinds", "/product-kinds").permitAll()
                .antMatchers("/users/save", "/users/delete/{id}", "/users/{id}/set-role/{id-role}").hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/users/{id}/recipes", "/users/{id}", "/users").permitAll()
                .antMatchers("/users/update/{id}").hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.USER.name())
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}

