package org.squeakytinkerings.ws.gen.templates;

import org.squeakytinkerings.util.ObjectTemplate;
import org.squeakytinkerings.ws.gen.WebServiceInfo;


public abstract class WebServiceDocumentTemplate implements ObjectTemplate {

	private WebServiceInfo webServiceInfo;
	
	public WebServiceDocumentTemplate(WebServiceInfo webServiceInfo) {
		setWebServiceInfo(webServiceInfo);
	}

	/**
	 * @param webServiceInfo the webServiceInfo to set
	 */
	public void setWebServiceInfo(WebServiceInfo webServiceInfo) {
		this.webServiceInfo = webServiceInfo;
	}

	/**
	 * @return the webServiceInfo
	 */
	public WebServiceInfo getWebServiceInfo() {
		return webServiceInfo;
	}
	
	public String getProjectName() {
		return getWebServiceInfo().getProjectName();
	}
		
	public String getCommandsNamespace() {
		return getWebServiceInfo().getCommandsNamespaceUri().toString();
	}
	
	public String getCommandsPackageName() {
		return getWebServiceInfo().getCommandsPackageName();
	}
		
	public String getDefaultUri() {
		return getWebServiceInfo().getDefaultUri().toString();
	}
		
	public String getRootPackageName() {
		return getWebServiceInfo().getRootPackageName();
	}
		
	public String getServiceNamespace() {
		return getWebServiceInfo().getServiceNamespaceUri().toString();
	}
		
	public String getTypesNamespace() {
		return getWebServiceInfo().getTypesNamespaceUri().toString();
	}
	
	public String getTypesPackageName() {
		return getWebServiceInfo().getTypesPackage().getName();
	}
	
	public String getServiceImplementationSimpleClassName() {
		return getWebServiceInfo().getServiceClass().getSimpleName();
	}
	
	public String getServiceImplementationFullyQualifiedClassName() {
		return getWebServiceInfo().getServiceClass().getName();
	}
}
