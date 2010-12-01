package org.squeakytinkerings.ws.gen;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.squeakytinkerings.util.ObjectTemplateInjector;
import org.squeakytinkerings.ws.gen.impl.SimpleWebServiceInfoFactory;
import org.squeakytinkerings.ws.gen.templates.ClientImplementationTemplate;
import org.squeakytinkerings.ws.gen.templates.ClientSpringConfigurationTemplate;
import org.squeakytinkerings.ws.gen.templates.CommandSchemaTemplate;
import org.squeakytinkerings.ws.gen.templates.ServerSpringConfigurationTemplate;
import org.squeakytinkerings.ws.gen.templates.WebXmlTemplate;
import org.squeakytinkerings.ws.gen.templates.WsdlTemplate;

public class TopDownWebServiceGenerator {
	
	private static final Log LOGGER =
		LogFactory.getLog(TopDownWebServiceGenerator.class);

	public static void main(String[] args) {
		TopDownWebServiceGenerator generator;
		
		if (args.length == 1) {
			
			AbstractWebServiceInfoFactory wsInfoFactory =
				new SimpleWebServiceInfoFactory();
			
			WebServiceInfo wsInfo;
			try {
				wsInfo = wsInfoFactory.generateWebServiceInfo(args[0]);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			generator = new TopDownWebServiceGenerator(wsInfo);
			generator.generate();
			
		} else {
			System.err.println("Need 1 argument!");
		}
	}

	private WebServiceInfo webServiceInfo;
	
	public TopDownWebServiceGenerator(WebServiceInfo wsInfo) {
		setWebServiceInfo(wsInfo);
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

	public void generate() {
		
		try {
			
			// Get an instance of an injector
			ObjectTemplateInjector injector =
				new ObjectTemplateInjector("generated");
			
			// Generate the web.xml file
			injector.inject(new WebXmlTemplate(getWebServiceInfo()));

			// Generate the WSDL
			injector.inject(new WsdlTemplate(getWebServiceInfo()));
			
			// Generate command classes
			CommandClassGeneratorFactory.getInstance()
					.getCommandClassGenerator(getWebServiceInfo(), injector)
							.generate();
			
			// Generate servlet Spring configuration
			injector.inject(
					new ServerSpringConfigurationTemplate(getWebServiceInfo()));
						
			// Generate client implementation class
			injector.inject(
					new ClientImplementationTemplate(getWebServiceInfo()));
			
			// Generate client Spring config file
			injector.inject(
					new ClientSpringConfigurationTemplate(getWebServiceInfo()));
			
			// Generate commands schema
			injector.inject(
					new CommandSchemaTemplate(getWebServiceInfo()));
		
		} catch (Exception e) {
			
			LOGGER.error(e);
			e.printStackTrace();
		}
	}
}
