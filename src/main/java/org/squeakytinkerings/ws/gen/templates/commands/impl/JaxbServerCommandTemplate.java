package org.squeakytinkerings.ws.gen.templates.commands.impl;

import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;
import org.squeakytinkerings.ws.gen.templates.commands.AbstractServerCommandTemplate;


/**
 * Generates a server command class.
 *  
 * @author anton.beza
 *
 */
public class JaxbServerCommandTemplate extends AbstractServerCommandTemplate {

	public JaxbServerCommandTemplate(
			WebServiceInfo webServiceInfo, WebServiceOperation operation) {
		super(webServiceInfo, operation);
	}

	@Override
	public String getGeneratedFilename() {
		return "src/main/java/"
			+ getCommandsPackageName().replaceAll("[.]", "/")
			+ "/"
			+ getCommandClassName()
			+ ".java";
	}
}
