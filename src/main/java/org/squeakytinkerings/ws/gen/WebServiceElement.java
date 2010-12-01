package org.squeakytinkerings.ws.gen;

import java.net.URI;

public class WebServiceElement {

	private String xsdElementName;
	
	private String xsdTypeName;
	
	private URI xsdNamespaceUri;
	
	private Class<?> implementationType;
	
	public WebServiceElement() {
		
	}
	
	public String getImplementationName() {
		return getXsdElementName().substring(0, 1).toLowerCase()
			+ getXsdElementName().substring(1);
	}

	/**
	 * @param xsdElementName the xsdElementName to set
	 */
	public void setXsdElementName(String xsdElementName) {
		this.xsdElementName = xsdElementName;
	}

	/**
	 * @return the xsdElementName
	 */
	public String getXsdElementName() {
		return xsdElementName;
	}

	/**
	 * @param xsdTypeName the xsdTypeName to set
	 */
	public void setXsdTypeName(String xsdTypeName) {
		this.xsdTypeName = xsdTypeName;
	}

	/**
	 * @return the xsdTypeName
	 */
	public String getXsdTypeName() {
		return xsdTypeName;
	}

	/**
	 * @param xsdNamespace the xsdNamespace to set
	 */
	public void setXsdNamespaceUri(URI xsdNamespace) {
		this.xsdNamespaceUri = xsdNamespace;
	}

	/**
	 * @return the xsdNamespace
	 */
	public URI getXsdNamespaceUri() {
		return xsdNamespaceUri;
	}

	/**
	 * @param implementationType the implementationType to set
	 */
	public void setImplementationType(Class<?> implementationType) {
		this.implementationType = implementationType;
	}

	/**
	 * @return the implementationType
	 */
	public Class<?> getImplementationType() {
		return implementationType;
	}
	
	public boolean needsConfiguration() {
		return (getXsdElementName() == null
				|| getXsdNamespaceUri() == null);
	}
	
	public boolean sameImplementation(WebServiceElement element) {
		return element.getImplementationType().equals(getImplementationType());
	}
	
	public void cloneImplementationFrom(WebServiceElement element) {
		setImplementationType(element.getImplementationType());
	}
}
