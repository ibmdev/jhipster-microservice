package fr.sma.bw.trace;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import lombok.extern.slf4j.Slf4j;

@Component
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
@Slf4j
public class ContentTraceFilter extends OncePerRequestFilter {
	
	protected ContentTraceManager traceManager;
    @Value("${management.trace.http.tracebody:false}")
    private boolean traceBody;
    
    public ContentTraceFilter(final ContentTraceManager traceManager) {
        super();
        this.traceManager = traceManager;
    }
	
	@Override
	protected void doFilterInternal(final HttpServletRequest request, 
			final HttpServletResponse response, 
			final FilterChain filterChain)
			throws ServletException, IOException {
		
		if (!isRequestValid(request) || !traceBody) {
            filterChain.doFilter(request, response);
            return;
        }
        final ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(
                request, 1000);
        final ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(
                response);
        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
            traceManager.updateBody(wrappedRequest, wrappedResponse);
        } finally {
            wrappedResponse.copyBodyToResponse();
        }
	}
	
	private boolean isRequestValid(final HttpServletRequest request) {
        try {
            new URI(request.getRequestURL().toString());
            return true;
        } catch (URISyntaxException ex) {
            return false;
        }
    }

	
	
}
