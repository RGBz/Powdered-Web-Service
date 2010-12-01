package org.squeakytinkerings.ws.gen.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import org.squeakytinkerings.ws.gen.AbstractWebServiceInfoFactory;
import org.squeakytinkerings.ws.gen.MarshallerChoice;
import org.squeakytinkerings.ws.gen.WebServiceElement;
import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;


public class SimpleWebServiceInfoFactory extends AbstractWebServiceInfoFactory {

	private Set<WebServiceOperation> operationConfiguration;
	
	private static final String WEB_SERVICE_OPERATIONS_CONFIG_FILENAME =
		"web-service-operations.configuration";
	
	public SimpleWebServiceInfoFactory() {
		
	}
		
	public WebServiceInfo generateWebServiceInfo(
			Class<?> serviceClass, MarshallerChoice mc) {
		WebServiceInfo wsInfo = new WebServiceInfo();
		
		loadWebServiceOperationConfiguration();
		
		wsInfo.setServiceClass(serviceClass);
		wsInfo.setMarshallerChoice(mc);
		
		// Build up the web service operations
		for (Method method : serviceClass.getMethods()) {
			WebServiceOperation operation = new WebServiceOperation();
			
			operation.setImplementationName(method.getName());
			
			operation.setCommandClassName(
					method.getName().substring(0, 1).toUpperCase()
					+ method.getName().substring(1)
					+ "Command");
			
			// Auto-configure return element
			if (method.getReturnType() != null) {
				operation.setReturns(autoConfigureElement(
						method.getReturnType()));
			}
			
			// Auto-configure parameters
			operation.setParameters(
					new WebServiceElement[method.getParameterTypes().length]);
			for (int i = 0; i < method.getParameterTypes().length; i++) {
				operation.getParameters()[i] = autoConfigureElement(
						method.getParameterTypes()[i]);
			}
			
			// Configure the XSD tie in via prompts if we need more details
			if (signatureNeedsConfiguration(operation)) {
				manuallyConfigureOperation(operation);
			}
			
			wsInfo.addOperation(operation);
		}
		
		saveWebServiceOperationConfiguration(wsInfo.getOperations());
		
		return wsInfo;
	}
	
	private WebServiceElement autoConfigureElement(Class<?> implementation) {
		WebServiceElement element = new WebServiceElement();
		
		element.setImplementationType(implementation);
		
		XmlElement details = implementation.getAnnotation(XmlElement.class);
		
		if (details != null) {
			element.setXsdElementName(details.name());
			try {
				element.setXsdNamespaceUri(new URI(details.namespace()));
			} catch (URISyntaxException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
			
		return element;
	}
	
	private void manuallyConfigureOperation(WebServiceOperation operation) {
		Console console = System.console();
		
		System.out.println(
				"Oh sugar, I'm a little confused and I need your help.");
		
		if (operation.getReturns() != null
				&& operation.getReturns().needsConfiguration()) {
			
			if (operation.getReturns().getXsdElementName() == null) {
				System.out.println(
						"What's the SCHEMA ELEMENT NAME for the RETURN VALUE "
						+ "for the " + operation.getImplementationName()
						+ " OPERATION?");
				String elementName = console.readLine();
				operation.getReturns().setXsdElementName(elementName);
			}
			
			if (operation.getReturns().getXsdNamespaceUri() == null) {
				System.out.println(
						"What's the SCHEMA ELEMENT NAMESPACE for the RETURN " +
						"VALUE for the " + operation.getImplementationName()
						+ " OPERATION?");
				String namespace = console.readLine();
				try {
					operation.getReturns().setXsdNamespaceUri(
							new URI(namespace));
				
				} catch (URISyntaxException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		
		for (int i = 0; i < operation.getParameters().length; i++) {
			WebServiceElement parameter = operation.getParameters()[i];
			
			if (parameter.getXsdElementName() == null) {
				System.out.println(
						"What's the SCHEMA ELEMENT NAME for the "
						+ getNumberFormatted(i + 1)
						+ " PARAMETER'S VALUE "
						+ "for the " + operation.getImplementationName()
						+ " OPERATION?");
				String elementName = console.readLine();
				parameter.setXsdElementName(elementName);
			}
			
			if (parameter.getXsdNamespaceUri() == null) {
				System.out.println(
						"What's the SCHEMA ELEMENT NAMESPACE for the "
						+ getNumberFormatted(i + 1)
						+ " PARAMETER'S VALUE "
						+ "for the " + operation.getImplementationName()
						+ " OPERATION?");
				String namespace = console.readLine();
				try {
					parameter.setXsdNamespaceUri(
							new URI(namespace));
				
				} catch (URISyntaxException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	private String getNumberFormatted(int i) {
		String suffix = "th";
		
		if (i % 10 == 1 && i != 11) {
			suffix = "st";
		
		} else if (i % 10 == 2 && i != 12) {
			suffix = "nd";
		
		} else if (i % 10 == 3 && i != 13) {
			suffix = "rd";
		}
		
		return i + suffix;
	}
	
	private boolean signatureNeedsConfiguration(WebServiceOperation operation) {
		for (WebServiceOperation configuredOperation
				: getOperationConfiguration()) {
			if (operation.sameImplementation(configuredOperation)) {
				operation.cloneImplementationFrom(configuredOperation);
				return false;
			}
		}
		
		if (operation.getReturns() != null
				&& operation.getReturns().needsConfiguration()) {
			return true;
		}
		
		for (WebServiceElement parameter : operation.getParameters()) {
			if (parameter.needsConfiguration()) {
				return true;
			}
		}
		
		return false;
	}
	
	private WebServiceElement parseWebServiceElementFromNotation(String str)
	throws ClassNotFoundException, URISyntaxException {
		if (str.equals("void")) {
			return null;
		}
		
		String[] main = str.split(":");
		String[] raw = main[1].split("@");
		WebServiceElement element = new WebServiceElement();
		
		element.setImplementationType(Class.forName(main[0]));
		element.setXsdElementName(raw[0]);
		
		if (raw.length < 2) {
			element.setXsdNamespaceUri(null);
		} else {
			element.setXsdNamespaceUri(new URI(raw[1]));
		}
		
		return element;
	}
	
	private void loadWebServiceOperationConfiguration() {
		setOperationConfiguration(new HashSet<WebServiceOperation>());
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					WEB_SERVICE_OPERATIONS_CONFIG_FILENAME));
			
			while(br.ready()) {
				String line = br.readLine();
				
				String[] tokens = line.trim().split(" ");
				
				WebServiceOperation operation = new WebServiceOperation();
				
				// Set the return details
				operation.setReturns(
						parseWebServiceElementFromNotation(tokens[0]));
				
				operation.setImplementationName(tokens[1]);
				
				WebServiceElement[] parameters =
					new WebServiceElement[tokens.length - 2];
				
				for (int i = 2; i < tokens.length - 1; i++) {
					int parameterIndex = i - 2;
					parameters[parameterIndex] =
						parseWebServiceElementFromNotation(tokens[i]);
				}
				
				operation.setParameters(parameters);
				
				getOperationConfiguration().add(operation);
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private String generateElementNotation(WebServiceElement element) {
		if (element == null) {
			return "void";
		} else {
			return element.getImplementationType().getName()
					+ ":"
					+ element.getXsdElementName()
					+ "@"
					+ element.getXsdNamespaceUri().toString();
		}
	}
	
	private void saveWebServiceOperationConfiguration(
			WebServiceOperation[] operations) {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
						WEB_SERVICE_OPERATIONS_CONFIG_FILENAME));
			
			for (WebServiceOperation operation : operations) {
				bw.write(generateElementNotation(operation.getReturns()));
				bw.write(" ");
				
				bw.write(operation.getImplementationName());
				bw.write(" ");
				
				for (WebServiceElement parameter : operation.getParameters()) {
					bw.write(generateElementNotation(parameter));
					bw.write(" ");
				}
				bw.write("\n");
			}
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @return the operationConfiguration
	 */
	private Set<WebServiceOperation> getOperationConfiguration() {
		return operationConfiguration;
	}

	/**
	 * @param operationConfiguration the operationConfiguration to set
	 */
	private void setOperationConfiguration(
			Set<WebServiceOperation> operationConfiguration) {
		this.operationConfiguration = operationConfiguration;
	}
}
