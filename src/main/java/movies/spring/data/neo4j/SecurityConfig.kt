package movies.spring.data.neo4j

import movies.spring.data.neo4j.infrastructure.security.TokenAuthenticationFilter
import movies.spring.data.neo4j.infrastructure.security.TokenAuthenticationProvider
import movies.spring.data.neo4j.repositories.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    val profileRepository: UserRepository,
    @Value("\${api.key}") val apiKey: String
) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(TokenAuthenticationFilter(authenticationManager()),
                BasicAuthenticationFilter::class.java)

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/authorization/**", "/public/**").permitAll()
                .antMatchers("/**").authenticated()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.authenticationProvider(tokenAuthenticationProvider())
    }

    @Bean
    fun tokenAuthenticationProvider() = TokenAuthenticationProvider(profileRepository, apiKey)
}
