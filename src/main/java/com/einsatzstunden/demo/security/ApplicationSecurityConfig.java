///*
//package com.einsatzstunden.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//@Configuration
//@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnable = true)    ---->   cette annotation permet de remplacer la configuration faite par les antMatchers() par la configuration des @PreAuthorize
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  private final PasswordEncoder passwordEncoder;
//
//  @Autowired
//  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
//    this.passwordEncoder=passwordEncoder;
//  }
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//
//    // NB: l'ordre des antMatchers compte. ecrire un antmatcher avant un autre et ensuite interchanger leurs positions
//    // pourrait changer la configuration
//
//    http
//        //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) -> pour utiliser les csrf token
//        .csrf().disable() // --> Cross Site Request Forgery
//        .authorizeRequests()
//        .antMatchers("/", "/home", "index", "/login", "/logout", "/css/*","/js/*").permitAll()
//        .antMatchers(HttpMethod.GET,"/api/mitarbeiter/all").hasRole(ApplicationRole.USER.name()) //authentification par role!!!!!!!
//        .antMatchers("/api/**").hasAuthority(ApplicationUserPermission.ADMIN_READ.name())// -> @PreAuthorize("hasAuthority('admin:read')")        avec @PreAuthorize au dessus de l'url dans le controlleur on peut remplacer les antMatchers
//        .antMatchers("/api/**").hasAuthority(ApplicationUserPermission.ADMIN_WRITE.name()) // -> @PreAuthorize("hasAuthority('admin:write')")
//        .antMatchers("/api/**").hasAuthority(ApplicationUserPermission.DEV_READ.name()) // -> @PreAuthorize("hasAuthority('dev:read')")
//        .antMatchers("/api/**").hasAuthority(ApplicationUserPermission.DEV_WRITE.name()) // -> @PreAuthorize("hasAuthority('dev:write')")
//        .antMatchers("/api/**").hasAnyRole(ApplicationRole.DEV.name(), ApplicationRole.ADMIN.name()) // -> @PreAuthorize("hasAnyRole('DEV', 'ADMIN')")
//        .anyRequest().authenticated()
//        .and()
//        .httpBasic(); //me donne le pop up pour me einloggen
//     // .formLogin(); //me donne une login page normal pour me einloggen
//     // .loginPage("/login"); //me donne une login page normal pour me einloggen
//
//  }
//
//  @Bean
//  @Override
//  public UserDetailsService userDetailsService() {
//    UserDetails pnkollo =
//        User.builder()
//            .username("ndpatrick")
//            .password(passwordEncoder.encode("password1"))
//            .roles(ApplicationRole.DEV.name())
//            .build();
//
//    UserDetails pnkollo2 =
//        User.builder()
//            .username("ndpatrick2")
//            .password(passwordEncoder.encode("password12"))
//            .authorities(ApplicationRole.DEV.getGrantedAuthorities())
//            //.roles("DEV")
//            .build();
//
//    UserDetails pmbogne =
//        User.builder()
//            .username("mbopierrette")
//            .password(passwordEncoder.encode("password2"))
//            .roles(ApplicationRole.ADMIN.name())
//            .build();
//
//    UserDetails pmbogne2 =
//        User.builder()
//            .username("mbopierrette2")
//            .password(passwordEncoder.encode("password22"))
//            //.roles(ApplicationRole.ADMIN.name())
//            .authorities(ApplicationRole.ADMIN.getGrantedAuthorities())
//            .build();
//
//    UserDetails disonkwa =
//        User.builder()
//            .username("sonkdimitri")
//            .password(passwordEncoder.encode("password3"))
//            .roles(ApplicationRole.USER.name())
//            .build();
//
//    UserDetails Fanijoyce =
//        User.builder()
//            .username("Fanijoyce")
//            .password(passwordEncoder.encode("password4"))
//            .roles(ApplicationRole.USER.name())
//            .build();
//
//    return new InMemoryUserDetailsManager(pnkollo, pmbogne, disonkwa, Fanijoyce, pmbogne2, pnkollo2);
//  }
//}
//
//*/
