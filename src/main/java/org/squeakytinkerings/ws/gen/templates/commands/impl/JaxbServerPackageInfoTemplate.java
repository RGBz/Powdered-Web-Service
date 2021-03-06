package org.squeakytinkerings.ws.gen.templates.commands.impl;

import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.templates.WebServiceDocumentTemplate;


public class JaxbServerPackageInfoTemplate extends WebServiceDocumentTemplate {

	public JaxbServerPackageInfoTemplate(WebServiceInfo webServiceInfo) {
		super(webServiceInfo);
	}

	@Override
	public String getGeneratedFilename() {
		return "package-info.java";
	}

	public String getCommandClassNames() {
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}
}
