package fr.sma.bw.trace;

import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final HttpTraceFilter httpTraceFilter;
    private final ContentTraceFilter contentTraceFilter;
    public WebSecurityConfig(final HttpTraceFilter httpTraceFilter,
            final ContentTraceFilter contentTraceFilter) {
        super();
        this.httpTraceFilter = httpTraceFilter;
        this.contentTraceFilter = contentTraceFilter;
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.addFilterBefore(contentTraceFilter,SecurityContextPersistenceFilter.class)
                .addFilterAfter(httpTraceFilter,SecurityContextPersistenceFilter.class)
                .authorizeRequests()
                .antMatchers("/**").permitAll();
                
    }
}