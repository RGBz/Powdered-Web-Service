package org.squeakytinkerings.ws.gen;

import java.util.Set;

public class WebServiceOperation {

	private String implementationName;
	
	private String commandClassName;
	
	private WebServiceElement returns;
	
	private WebServiceElement[] parameters;
	
	private Set<Class<? extends Throwable>> exceptions;
	
	public WebServiceOperation() {
		
	}

	/**
	 * @return the implementationName
	 */
	public String getImplementationName() {
		return implementationName;
	}

	/**
	 * @param implementationName the implementationName to set
	 */
	public void setImplementationName(String implementationName) {
		this.implementationName = implementationName;
	}

	/**
	 * @return the commandClassName
	 */
	public String getCommandClassName() {
		return commandClassName;
	}

	/**
	 * @param commandClassName the commandClassName to set
	 */
	public void setCommandClassName(String commandClassName) {
		this.commandClassName = commandClassName;
	}

	/**
	 * @return the returns
	 */
	public WebServiceElement getReturns() {
		return returns;
	}

	/**
	 * @param returns the returns to set
	 */
	public void setReturns(WebServiceElement returns) {
		this.returns = returns;
	}

	/**
	 * @return the parameters
	 */
	public WebServiceElement[] getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(WebServiceElement[] parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the exceptions
	 */
	public Set<Class<? extends Throwable>> getExceptions() {
		return exceptions;
	}

	/**
	 * @param exceptions the exceptions to set
	 */
	public void setExceptions(Set<Class<? extends Throwable>> exceptions) {
		this.exceptions = exceptions;
	}
	
	public boolean sameImplementation(WebServiceOperation operation) {
		if (!operation.getImplementationName().equals(
				getImplementationName())
				|| (operation.getReturns() != null &&
						!operation.getReturns().sameImplementation(
								getReturns()))
				|| operation.getParameters().length != getParameters().length) {
			return false;
		}
		
		for (int i = 0; i < getParameters().length; i++) {
			if(!getParameters()[i].sameImplementation(
					operation.getParameters()[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	public void cloneImplementationFrom(WebServiceOperation operation) {
		setImplementationName(operation.getImplementationName());
		
		if (getReturns() != null) {
			getReturns().cloneImplementationFrom(operation.getReturns());
		}
		
		for (int i = 0; i < getParameters().length; i++) {
			getParameters()[i].cloneImplementationFrom(
					operation.getParameters()[i]);
		}
	}
}
