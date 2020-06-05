package fr.sma.bw.trace;

import java.util.List;

public class ContentTraceDescriptor {
	
	protected List<ContentTrace> traces;
    public ContentTraceDescriptor(final List<ContentTrace> traces) {
        super();
        this.traces = traces;
    }
    public List<ContentTrace> getTraces() {
        return traces;
    }
    public void setTraces(final List<ContentTrace> traces) {
        this.traces = traces;
    }
}
