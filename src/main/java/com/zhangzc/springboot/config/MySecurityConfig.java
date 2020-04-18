package com.zhangzc.springboot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * https://docs.spring.io/spring-security/site/docs/current/guides/helloworld-boot.html
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/webjars/**","/favicon.ico","/asserts/**","/user/login").permitAll()
                    .antMatchers("/query/**").hasRole("USER")
                    .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/user/login").defaultSuccessUrl("/main")
                .and()
                .logout()
                .and()
                .rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .withUser("user")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("USER");
    }

    /*public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception { //配置策略
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/static/**").permitAll().anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler())
                    .and()
                    .logout().permitAll().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler())
                    .and()
                    .rememberMe()
                    .and().sessionManagement().maximumSessions(10).expiredUrl("/login");
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
            auth.eraseCredentials(false);
        }

        @Bean
        public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
            return new TokenBasedRememberMeServices("springRocks", userDetailsService());
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() { //密码加密
            return new BCryptPasswordEncoder(4);
        }


        @Bean
        public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
            return (httpServletRequest, httpServletResponse, authentication) -> {
                try {
                    SecurityUser user = (SecurityUser) authentication.getPrincipal();
                    log.info("USER : {} LOGOUT SUCCESS ! ", user.getUsername());
                } catch (Exception e) {
                    log.error("printStackTrace", e);
                }
                httpServletResponse.sendRedirect("/login");
            };
        }

        @Bean
        public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
            return new SavedRequestAwareAuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                    User userDetails = (User) authentication.getPrincipal();
                    log.info("USER : {} LOGIN SUCCESS !  ", userDetails.getUsername());
                    super.onAuthenticationSuccess(request, response, authentication);
                }
            };
        }


        @Bean
        @Override
        public UserDetailsService userDetailsService() {    //用户登录实现
            return new UserDetailsService() {
                @Autowired
                private UserRepository userRepository;

                @Override
                public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                    User user = userRepository.findByUsername(s);
                    if (user == null) throw new UsernameNotFoundException("Username " + s + " not found");
                    return new SecurityUser(user);
                }
            };
        }

    }
    */
}
