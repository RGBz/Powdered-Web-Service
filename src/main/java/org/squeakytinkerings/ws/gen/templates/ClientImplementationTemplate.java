package org.squeakytinkerings.ws.gen.templates;

import org.squeakytinkerings.patterns.Command;
import org.squeakytinkerings.patterns.ResponseCommand;
import org.squeakytinkerings.ws.gen.WebServiceElement;
import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;


/**
 * Generates the client-side class to facilitate web service access.
 * 
 * @author anton.beza
 *
 */
public class ClientImplementationTemplate
extends WebServiceDocumentTemplate {

	public ClientImplementationTemplate(WebServiceInfo webServiceInfo) {
		super(webServiceInfo);
	}
	
	public String getClientOperations() {
		StringBuffer clientOperations = new StringBuffer();
		
		for (WebServiceOperation operation
				: getWebServiceInfo().getOperations()) {
			
			String operationName = operation.getImplementationName();
			
			String clientCommandName = 
				getWebServiceInfo().getRootPackageName() + ".commands.client."
				+ "Client" + operation.getCommandClassName();
			
			Class<?> outputType =
				operation.getReturns().getImplementationType();
			
			// Import the correct command interface based on return type
			// as well as set up the return type
			if (outputType == null) {
				clientOperations.append("    public void ");
				
			} else {
				clientOperations.append("    public ");
				clientOperations.append(outputType.getName());
				clientOperations.append(" ");
			}
			
			clientOperations.append(operationName);
			clientOperations.append("(");
			
			int parameterIndex = 0;
			
			// Generate the parameters
			for (WebServiceElement parameter : operation.getParameters()) {
			
				Class<?> inputType = parameter.getImplementationType();
				
				if (parameterIndex > 0) {
					clientOperations.append(", ");
				}
				
				clientOperations.append(inputType.getName());
				clientOperations.append(" arg");
				clientOperations.append(parameterIndex);
				
				parameterIndex += 1;
			}
			
			clientOperations.append(") {\n        ");
			
			if (outputType == null) {
				clientOperations.append(Command.class.getName());
				
			} else {
				clientOperations.append(ResponseCommand.class.getName());
				clientOperations.append("<");
				clientOperations.append(outputType.getName());
				clientOperations.append(">");
			}
			
			clientOperations.append(" command = new ");
			clientOperations.append(clientCommandName);
			clientOperations.append("(");
			
			// Insert the arguments
			for (int argIndex = 0; argIndex < operation.getParameters().length;
					argIndex++) {
			
				if (argIndex > 0) {
					clientOperations.append(", ");
				}
				clientOperations.append("arg");
				clientOperations.append(argIndex);
			}
			
			clientOperations.append(");\n");
			clientOperations.append("        command.execute();\n");
			clientOperations.append("    }\n\n");
		}
		
		return clientOperations.toString();
	}

	@Override
	public String getGeneratedFilename() {
		return "src/main/java/"
			+ getRootPackageName().replaceAll("[.]", "/")
			+ "/Client"
			+ getServiceImplementationSimpleClassName()
			+ ".java";
	}
}
