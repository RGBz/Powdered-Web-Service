package org.squeakytinkerings.ws.gen;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class WebServiceInfo {

	private String projectName;
	private Class<?> serviceClass;
	
	private Set<WebServiceOperation> operations;
	private String rootPackageName;
	
	private URI defaultUri;
	
	private URI typesNamespaceUri;
	private Package typesPackage;
	
	private URI commandsNamespaceUri;
	private String commandsPackageName;
	
	private URI serviceNamespaceUri;

	/*
	 * TODO handle exceptions through the process
	 */
	private Set<Class<? extends Throwable>> exceptions;
	
	private MarshallerChoice marshallerChoice;
		
	public WebServiceInfo() {
		operations = new HashSet<WebServiceOperation>();
		exceptions = new HashSet<Class<? extends Throwable>>();
	}

	/**
	 * @return the serviceClass
	 */
	public Class<?> getServiceClass() {
		return serviceClass;
	}

	/**
	 * @param serviceClass the serviceName to set
	 */
	public void setServiceClass(Class<?> serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * @return the serviceOperations
	 */
	public WebServiceOperation[] getOperations() {
		return operations.toArray(new WebServiceOperation[operations.size()]);
	}

	/**
	 * @param serviceOperation the serviceOperation to set
	 */
	public void addOperation(WebServiceOperation serviceOperation) {
		this.operations.add(serviceOperation);
	}

	/**
	 * @return the typesNamespace
	 */
	public URI getTypesNamespaceUri() {
		return typesNamespaceUri;
	}

	/**
	 * @param typesNamespace the typesNamespace to set
	 */
	public void setTypesNamespaceUri(URI typesNamespace) {
		this.typesNamespaceUri = typesNamespace;
	}

	/**
	 * @return the commandsNamespace
	 */
	public URI getCommandsNamespaceUri() {
		return commandsNamespaceUri;
	}

	/**
	 * @param commandsNamespace the commandsNamespace to set
	 */
	public void setCommandsNamespaceUri(URI commandsNamespace) {
		this.commandsNamespaceUri = commandsNamespace;
	}

	/**
	 * @return the serviceNamespace
	 */
	public URI getServiceNamespaceUri() {
		return serviceNamespaceUri;
	}

	/**
	 * @param serviceNamespace the serviceNamespace to set
	 */
	public void setServiceNamespaceUri(URI serviceNamespace) {
		this.serviceNamespaceUri = serviceNamespace;
	}

	/**
	 * @return the exceptions
	 */
	public Set<Class<? extends Throwable>> getExceptions() {
		return exceptions;
	}

	/**
	 * @param exception the exception to add
	 */
	public void addException(Class<? extends Throwable> exception) {
		this.exceptions.add(exception);
	}

	/**
	 * @return the marshallerChoice
	 */
	public MarshallerChoice getMarshallerChoice() {
		return marshallerChoice;
	}

	/**
	 * @param marshallerChoice the marshallerChoice to set
	 */
	public void setMarshallerChoice(MarshallerChoice marshallerChoice) {
		this.marshallerChoice = marshallerChoice;
	}

	/**
	 * @param typesPackage the typesPackage to set
	 */
	public void setTypesPackage(Package typesPackage) {
		this.typesPackage = typesPackage;
	}

	/**
	 * @return the typesPackage
	 */
	public Package getTypesPackage() {
		return typesPackage;
	}

	/**
	 * @param commandsPackage the commandsPackage to set
	 */
	public void setCommandsPackageName(String commandsPackage) {
		this.commandsPackageName = commandsPackage;
	}

	/**
	 * @return the commandsPackage
	 */
	public String getCommandsPackageName() {
		return commandsPackageName;
	}

	/**
	 * @param defaultUri the defaultUri to set
	 */
	public void setDefaultUri(URI defaultUri) {
		this.defaultUri = defaultUri;
	}

	/**
	 * @return the defaultUri
	 */
	public URI getDefaultUri() {
		return defaultUri;
	}

	/**
	 * @param rootPackageName the rootPackageName to set
	 */
	public void setRootPackageName(String rootPackageName) {
		this.rootPackageName = rootPackageName;
	}

	/**
	 * @return the rootPackageName
	 */
	public String getRootPackageName() {
		return rootPackageName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
}
