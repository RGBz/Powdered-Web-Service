package org.squeakytinkerings.ws.gen.templates;

import org.squeakytinkerings.ws.gen.WebServiceInfo;

public class WebXmlTemplate extends WebServiceDocumentTemplate {

	public WebXmlTemplate(WebServiceInfo webServiceInfo) {
		super(webServiceInfo);
	}

	@Override
	public String getGeneratedFilename() {
		return "webapp/WEB-INF/web.xml";
	}
}
