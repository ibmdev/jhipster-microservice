package fr.sma.bw.trace;

import org.springframework.boot.actuate.trace.http.HttpTrace;

public class ContentTrace {
	
	protected HttpTrace httpTrace;
    protected String requestBody;
    protected String responseBody;
	public HttpTrace getHttpTrace() {
		return httpTrace;
	}
	public void setHttpTrace(final HttpTrace httpTrace) {
		this.httpTrace = httpTrace;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(final String requestBody) {
		this.requestBody = requestBody;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(final String responseBody) {
		this.responseBody = responseBody;
	}
    
    
}
