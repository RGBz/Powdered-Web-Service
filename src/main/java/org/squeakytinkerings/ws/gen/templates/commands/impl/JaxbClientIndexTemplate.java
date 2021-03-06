package org.squeakytinkerings.ws.gen.templates.commands.impl;

import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.templates.WebServiceDocumentTemplate;


public class JaxbClientIndexTemplate extends WebServiceDocumentTemplate {

	public JaxbClientIndexTemplate(WebServiceInfo webServiceInfo) {
		super(webServiceInfo);
	}

	@Override
	public String getGeneratedFilename() {
		return "jaxb.index";
	}

	public String getCommandClassNames() {
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}
}
