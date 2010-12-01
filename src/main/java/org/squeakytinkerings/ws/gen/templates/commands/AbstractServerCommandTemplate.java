package org.squeakytinkerings.ws.gen.templates.commands;

import org.squeakytinkerings.patterns.Command;
import org.squeakytinkerings.patterns.ResponseCommand;
import org.squeakytinkerings.ws.gen.WebServiceElement;
import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;


/**
 * Generates a server command class.
 *  
 * @author anton.beza
 *
 */
public abstract class AbstractServerCommandTemplate
extends AbstractCommandTemplate {
	
	public AbstractServerCommandTemplate(
			WebServiceInfo webServiceInfo, WebServiceOperation operation) {
		super(webServiceInfo, operation);
	}
	
	@Override
	public String getSuperClass() {
		if (getOperation().getReturns() == null) {
			return Command.class.getName();
		} else {
			return ResponseCommand.class.getName();
		}
	}
	
	public String getOperationReturnType() {
		WebServiceElement returns = getOperation().getReturns();
		if (returns == null) {
			return "void";
		} else {
			return returns.getImplementationType().getName();
		}
	}
	
	public String getOperationArguments() {
		StringBuffer sb = new StringBuffer();
		
		for (int parameterIndex = 0;
				parameterIndex < getOperation().getParameters().length;
				parameterIndex++) {
			
			if (parameterIndex > 0) {
				sb.append(", ");
			}
			
			sb.append("getAttribute");
			sb.append(parameterIndex);
			sb.append("()");
		}
		return sb.toString();
	}
}
