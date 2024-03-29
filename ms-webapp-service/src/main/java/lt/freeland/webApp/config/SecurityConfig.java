package lt.freeland.webApp.config;

import javax.servlet.Filter;
import lt.freeland.webApp.beans.UserAuthSuccessHandler;
import lt.freeland.webApp.beans.SsoLogoutHandler;
import lt.freeland.webApp.beans.UserLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author freeland
 */
@EnableOAuth2Client
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.oauth2.resource.ssoLogoutUri}")
    String logoutUrl;

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Autowired
    UserAuthSuccessHandler userAuthSuccessHandler;

    @Autowired
    SsoLogoutHandler ssoLogoutHandler;

    @Autowired
    UserLogoutSuccessHandler userLogoutSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/oauthCallback");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/css/**", "/oauthCallback").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin().loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl(logoutUrl + "?redirect_uri=login?logout")
                .addLogoutHandler(ssoLogoutHandler)
                .permitAll();

        http.
                addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter oauthFilter = new OAuth2ClientAuthenticationProcessingFilter("/login");
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(uaaClientProperties(), oauth2ClientContext);
        oauthFilter.setRestTemplate(restTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(uaaServerResource().getUserInfoUri(), uaaClientProperties().getClientId());
        tokenServices.setRestTemplate(restTemplate);
        oauthFilter.setTokenServices(tokenServices);
        userAuthSuccessHandler.setDefaultTargetUrl("/dashboard");
        oauthFilter.setAuthenticationSuccessHandler(userAuthSuccessHandler);
        return oauthFilter;
    }

    @Bean
    @ConfigurationProperties("security.oauth2.client")
    public AuthorizationCodeResourceDetails uaaClientProperties() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("security.oauth2.resource")
    public ResourceServerProperties uaaServerResource() {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }
}
