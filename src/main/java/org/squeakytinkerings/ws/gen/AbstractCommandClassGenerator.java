package org.squeakytinkerings.ws.gen;

import org.squeakytinkerings.util.ObjectTemplateInjector;
import org.squeakytinkerings.ws.gen.templates.PackageInfoTemplate;


public abstract class AbstractCommandClassGenerator {
	
	private ObjectTemplateInjector injector;
	private WebServiceInfo webServiceInfo;

	public AbstractCommandClassGenerator(
			WebServiceInfo wsInfo, ObjectTemplateInjector injector) {
		setWebServiceInfo(wsInfo);
		setInjector(injector);
	}
	
	public abstract void generate();
	
	protected void generatePackageInfo() {
		getInjector().inject(new PackageInfoTemplate(getWebServiceInfo()));
	}

	/**
	 * @param injector the injector to set
	 */
	private void setInjector(ObjectTemplateInjector injector) {
		this.injector = injector;
	}

	/**
	 * @return the injector
	 */
	protected ObjectTemplateInjector getInjector() {
		return injector;
	}

	/**
	 * @param webServiceInfo the webServiceInfo to set
	 */
	private void setWebServiceInfo(WebServiceInfo webServiceInfo) {
		this.webServiceInfo = webServiceInfo;
	}

	/**
	 * @return the webServiceInfo
	 */
	protected WebServiceInfo getWebServiceInfo() {
		return webServiceInfo;
	}
}
