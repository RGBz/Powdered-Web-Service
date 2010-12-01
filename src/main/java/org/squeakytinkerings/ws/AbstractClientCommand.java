package org.squeakytinkerings.ws;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.client.core.WebServiceOperations;
import org.squeakytinkerings.patterns.Command;


public class AbstractClientCommand implements Command {
	
	private static final String SPRING_CLIENT_XML_PATH =
		"ws-template.xml";
	
	private static final String WEB_SERVICE_TEMPLATE_BEAN_ID =
		"webServiceTemplate";

	public AbstractClientCommand() {

	}
	
	@Override
	public final void execute() {
		Resource resource =
			new ClassPathResource(SPRING_CLIENT_XML_PATH);
		XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
		((WebServiceOperations)
				beanFactory.getBean(WEB_SERVICE_TEMPLATE_BEAN_ID))
				.marshalSendAndReceive(this);
	}
}
