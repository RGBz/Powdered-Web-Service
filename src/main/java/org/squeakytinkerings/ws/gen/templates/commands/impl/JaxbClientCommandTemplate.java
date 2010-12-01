package org.squeakytinkerings.ws.gen.templates.commands.impl;

import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;
import org.squeakytinkerings.ws.gen.templates.commands.AbstractClientCommandTemplate;


public class JaxbClientCommandTemplate extends AbstractClientCommandTemplate {
	
	public JaxbClientCommandTemplate(
			WebServiceInfo webServiceInfo, WebServiceOperation operation) {
		super(webServiceInfo, operation);
	}

	@Override
	public String getGeneratedFilename() {
		return "src/main/java/"
			+ getCommandsPackageName().replaceAll("[.]", "/")
			+ "/client/Client"
			+ getCommandClassName()
			+ ".java";
	}
}
