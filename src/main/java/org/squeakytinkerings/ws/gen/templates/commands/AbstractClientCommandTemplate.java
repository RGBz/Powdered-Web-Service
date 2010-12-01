package org.squeakytinkerings.ws.gen.templates.commands;

import org.squeakytinkerings.ws.AbstractClientCommand;
import org.squeakytinkerings.ws.AbstractClientResponseCommand;
import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;

public abstract class AbstractClientCommandTemplate
extends AbstractCommandTemplate {
		
	public AbstractClientCommandTemplate(
			WebServiceInfo webServiceInfo, WebServiceOperation operation) {
		super(webServiceInfo, operation);
	}
	
	@Override
	public String getSuperClass() {
		if (getOperation().getReturns() == null) {
			return AbstractClientCommand.class.getName();
		} else {
			return AbstractClientResponseCommand.class.getName();
		}
	}
}
