package switchtwentytwenty.project.autentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        //securedEnabled = true,
       //  jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //US -> Authentication
                .antMatchers("/auth/**").permitAll()
                //Menu Options
                .antMatchers("/").permitAll()
                //US001
                .antMatchers(HttpMethod.POST,"/categories/standard").access("hasRole('ROLE_SYSTEM_MANAGER')")
                .antMatchers(HttpMethod.GET,"/categories/standard").permitAll()
                //US002
                .antMatchers("/categories/standard/tree").permitAll()
                //US010 & list of families
                .antMatchers(HttpMethod.POST,"/families").access("hasRole('ROLE_SYSTEM_MANAGER')")
                //.antMatchers(HttpMethod.GET,"/families").permitAll()
                .antMatchers(HttpMethod.GET,"/families").access("hasRole('ROLE_SYSTEM_MANAGER')")
                //US101 & list of family members
                .antMatchers(HttpMethod.POST,"/users").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/families/{familyID}/users").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US104
                .antMatchers(HttpMethod.GET, "/families/relations/{id}").permitAll()
                //US105 & list of all possible relations & US106
                .antMatchers( HttpMethod.POST,"/families/{familyID}/relations").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/families/relations").permitAll()
                //US110

                //US120
                .antMatchers(HttpMethod.POST, "/families/{familyID}/familyCashAccount").access("hasRole('ROLE_ADMIN')")
                //US150
                .antMatchers(HttpMethod.GET, "/users/{id}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

                //US151 && US152
                .antMatchers(HttpMethod.POST,"/users/{personId}/email").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.DELETE, "/users/{personId}/email").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US111

                //US130 & US180 -> TODO: //rever esta opção
                .antMatchers("/accounts/{accountID}/transfer/{accountID}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US135
                .antMatchers(HttpMethod.POST, "/users/{personId}/accounts/{accountId}/balance").access("hasRole('ROLE_ADMIN')")
                //US170
                .antMatchers(HttpMethod.POST,"/users/{personId}/personalCashAccount").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US171 & 172 & 173
                .antMatchers(HttpMethod.POST,"/accounts/bank").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US181
                .antMatchers(HttpMethod.POST,"/accounts/{accountID}/payment").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US185
                .antMatchers("/accounts/{accountID}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US186
                .antMatchers("/accounts/{accountID}/movements").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US188

                //US Get family profiles
                .antMatchers(HttpMethod.GET,"/families/{familyID}").permitAll()
                //US Accounts options
                .antMatchers(HttpMethod.OPTIONS, "/accounts").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US Get Person Ledger
                .antMatchers(HttpMethod.GET,"/users/{personID}/ledger").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //US Get Family Leger
                .antMatchers(HttpMethod.GET,"/families/{familyID}/ledger").access("hasRole('ROLE_ADMIN')")
                //Run testes Authorization
                .antMatchers("/h2-console/**").permitAll()
                //Authentication Required
                .anyRequest().authenticated();

        http.headers().frameOptions().disable();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
