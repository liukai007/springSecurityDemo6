package com.lk.demo5.config;

import com.lk.demo5.other.Mobile1AuthenticationFailureHandler;
import com.lk.demo5.other.Mobile1AuthenticationSuccessHandler;
import com.lk.demo5.other.MobileAuthenticationProcessingFilter;
import com.lk.demo5.other.MobileAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private MobileAuthenticationProvider mobileAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.addFilterBefore(mobileAuthenticationProcessingFilter(), AbstractPreAuthenticatedProcessingFilter.class);
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/loginceshi").permitAll()
                .antMatchers(HttpMethod.GET,"/failure").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //将多个自定义认证器都注册
        auth.authenticationProvider(mobileAuthenticationProvider());

    }

    @Bean
    public MobileAuthenticationProvider mobileAuthenticationProvider() {
        return new MobileAuthenticationProvider();
    }

    @Bean
    public MobileAuthenticationProcessingFilter mobileAuthenticationProcessingFilter() {
        MobileAuthenticationProcessingFilter filter = new MobileAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(new Mobile1AuthenticationSuccessHandler("/success"));
        filter.setAuthenticationFailureHandler(new Mobile1AuthenticationFailureHandler("/failure"));
        filter.setFilterProcessesUrl("/login");
        return filter;
    }


    /**
     * 忽略拦截url或静态资源文件夹
     * <p>
     * //     * @param web
     *
     * @throws Exception
     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.POST,
//                "/login");
//    }
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
