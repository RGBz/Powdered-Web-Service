package org.squeakytinkerings.ws.gen.templates;

import org.squeakytinkerings.ws.gen.WebServiceInfo;

public class ClientSpringConfigurationTemplate
extends WebServiceDocumentTemplate {
	
	public ClientSpringConfigurationTemplate(WebServiceInfo webServiceInfo) {
		super(webServiceInfo);
	}

	@Override
	public String getGeneratedFilename() {
		return "ws-template.xml";
	}
}
