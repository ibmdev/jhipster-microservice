package fr.sma.bw.trace;

import java.util.List;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.trace.http.HttpTraceEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@EndpointWebExtension(endpoint = HttpTraceEndpoint.class)
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
public class HttpTraceEndpointExtension {
	
	private final JournalTechniqueTraceRepository repository;
    public HttpTraceEndpointExtension(final JournalTechniqueTraceRepository repository) {
        super();
        this.repository = repository;
    }
    @ReadOperation
    public ContentTraceDescriptor contents() {
        final List<ContentTrace> traces = repository.findAllWithContent();
        return new ContentTraceDescriptor(traces);
    	
    }
    

}
