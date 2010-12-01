package org.squeakytinkerings.ws.server.endpoint;


import org.springframework.ws.server.endpoint.AbstractMarshallingPayloadEndpoint;
import org.squeakytinkerings.patterns.Command;

public class CommandEndpoint extends AbstractMarshallingPayloadEndpoint {

	@Override
	protected Object invokeInternal(Object command) throws Exception {
		
		((Command) command).execute();
		
		return null;
	}
}
