package org.squeakytinkerings.ws.server.endpoint;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.ws.server.endpoint.AbstractMarshallingPayloadEndpoint;
import org.squeakytinkerings.patterns.ResponseCommand;

public class ResponseCommandEndpoint
extends AbstractMarshallingPayloadEndpoint {
	
	private static final Log LOGGER =
		LogFactory.getLog(ResponseCommandEndpoint.class);
	
	public ResponseCommandEndpoint(Marshaller marshaller) {
		super(marshaller);
	}

	@Override
	protected Object invokeInternal(Object command) throws Exception {
		Object response;
		
		LOGGER.debug("Received command: " + command.getClass().getName());
		
		response = ((ResponseCommand<?>) command).execute();
		
		LOGGER.debug("Generated response: ");
		LOGGER.debug(response.getClass().getName());
		LOGGER.debug(response.toString());
		
		return response;
	}
}
