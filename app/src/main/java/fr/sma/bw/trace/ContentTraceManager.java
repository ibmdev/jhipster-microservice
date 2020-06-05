package fr.sma.bw.trace;

import java.io.UnsupportedEncodingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import fr.sma.bw.builders.JournalTechniqueBuilder;
import fr.sma.bw.entities.JournalTechnique;
import fr.sma.bw.repositories.mybatis.JournalTechniqueDao;
import lombok.extern.slf4j.Slf4j;

@Component
@RequestScope
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
@Slf4j
public class ContentTraceManager {
	
	private ContentTrace trace;
	private final JournalTechniqueDao journalTechniqueDao;
	
    public ContentTraceManager(final JournalTechniqueDao journalTechniqueDao) {
		super();
		this.journalTechniqueDao = journalTechniqueDao;
	}

	public void updateBody(final ContentCachingRequestWrapper wrappedRequest,
            final ContentCachingResponseWrapper wrappedResponse) {

        final String requestBody = getRequestBody(wrappedRequest);
        getTrace().setRequestBody(requestBody);
        final String responseBody = getResponseBody(wrappedResponse);
        getTrace().setResponseBody(responseBody);
        persist(trace);
    }

    protected String getRequestBody(final ContentCachingRequestWrapper wrappedRequest) {
        try {
            if (wrappedRequest.getContentLength() <= 0) {
                return null;
            }
            return new String(wrappedRequest.getContentAsByteArray(), 0,
                    wrappedRequest.getContentLength(),
                    wrappedRequest.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            if(log.isErrorEnabled()) {
            	log.error("Message d'erreur: " + e.getMessage());
            }
        	return null;
        }

    }

    protected String getResponseBody(final ContentCachingResponseWrapper wrappedResponse) {

        try {
            if (wrappedResponse.getContentSize() <= 0) {
                return null;
            }
            return new String(wrappedResponse.getContentAsByteArray(), 0,
                    wrappedResponse.getContentSize(),
                    wrappedResponse.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
        	if(log.isErrorEnabled()) {
        		log.error("Message erreur: " + e.getMessage());
            }
        	return null;
        }

    }

    public ContentTrace getTrace() {
        if (trace == null) {
            trace = new ContentTrace();
        }
        return trace;
    }
    
    private void persist(final ContentTrace trace) {
    	final JournalTechnique journalTechnique = new JournalTechniqueBuilder(
    			"",
    			"",
    			trace.getHttpTrace().getTimestamp().toString(),
    			String.valueOf(trace.getHttpTrace().getResponse().getStatus()),
    			trace.getHttpTrace().getResponse().getStatus())
    	.build();
    	this.journalTechniqueDao.save(journalTechnique);
    }

}
