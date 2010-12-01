package org.squeakytinkerings.ws.gen.templates.commands.impl;

import org.squeakytinkerings.util.ObjectTemplateInjector;
import org.squeakytinkerings.ws.gen.AbstractCommandClassGenerator;
import org.squeakytinkerings.ws.gen.WebServiceInfo;


/**
 * TODO finish setting up Jaxb template classes and files
 * 
 * TODO Create a WebServiceInfo factory that generates the appropriate factory
 * from the Service Implementation class
 * 
 * TODO Turn the WebServiceInfo class into a bean
 * 
 * TODO Finish the generate() method on this class
 * 
 * TODO Test like crazy
 * 
 * @author anton.beza
 *
 */
public class JaxbCommandClassGenerator extends AbstractCommandClassGenerator {

	public JaxbCommandClassGenerator(WebServiceInfo wsInfo,
			ObjectTemplateInjector injector) {
		super(wsInfo, injector);
	}

	@Override
	public void generate() {
		// TODO Auto-generated method stub

	}
}
