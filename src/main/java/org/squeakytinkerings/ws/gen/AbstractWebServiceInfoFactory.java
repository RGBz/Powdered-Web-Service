package org.squeakytinkerings.ws.gen;

public abstract class AbstractWebServiceInfoFactory {

	public AbstractWebServiceInfoFactory() {
		
	}
	
	public final WebServiceInfo generateWebServiceInfo(String serviceClassName)
	throws ClassNotFoundException {
		return generateWebServiceInfo(Class.forName(serviceClassName));
	}
	
	public final WebServiceInfo generateWebServiceInfo(Class<?> serviceClass) {
		return generateWebServiceInfo(serviceClass, MarshallerChoice.JAXB);
	}
	
	public final WebServiceInfo generateWebServiceInfo(
			String serviceClassName, MarshallerChoice mc)
	throws ClassNotFoundException {
		return generateWebServiceInfo(Class.forName(serviceClassName), mc);
	}
	
	public abstract WebServiceInfo generateWebServiceInfo(
			Class<?> serviceClass, MarshallerChoice mc);
}
