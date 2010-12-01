package org.squeakytinkerings.ws.gen.templates;

import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;
import org.squeakytinkerings.ws.server.endpoint.CommandEndpoint;
import org.squeakytinkerings.ws.server.endpoint.ResponseCommandEndpoint;

public class ServerSpringConfigurationTemplate
extends WebServiceDocumentTemplate {
	
	public ServerSpringConfigurationTemplate(WebServiceInfo webServiceInfo) {
		super(webServiceInfo);
	}

	public String getOperationEndpoints() {
		StringBuffer sb = new StringBuffer();
		
		boolean usesCommandEndpoint = false;
		boolean usesResponseCommandEndpoint = false;
		
		for (WebServiceOperation operation
        		: getWebServiceInfo().getOperations()) {
            
            if (operation.getReturns() == null) {
            	
            	if (!usesCommandEndpoint) {
            		
            		sb.append("    <spring:bean id=\"");
            		sb.append("CommandEndpoint\"\n");
                    sb.append("        class=\"");
                    sb.append(CommandEndpoint.class.getName());
                    sb.append("\">\n\n");
                    sb.append("      <spring:constructor-arg ref=\"marshaller\"/>\n");
                    sb.append("    </spring:bean>\n\n");
                    
                    usesCommandEndpoint = true;
            	}
            	
            } else {
            	
            	if (!usesResponseCommandEndpoint) {
            		
            		sb.append("    <spring:bean id=\"");
            		sb.append("ResponseCommandEndpoint\"\n");
                    sb.append("        class=\"");
                    sb.append(ResponseCommandEndpoint.class.getName());
                    sb.append("\">\n\n");
                    sb.append("      <spring:constructor-arg ref=\"marshaller\"/>\n");
                    sb.append("    </spring:bean>\n\n");
                    
                    usesResponseCommandEndpoint = true;
            	}
            }
            
            if (usesCommandEndpoint && usesResponseCommandEndpoint) {
            	break;
            }
        }
        return sb.toString();
	}
	
	public String getEndpointMappings() {
        StringBuffer sb = new StringBuffer();
        
        for (WebServiceOperation operation
        		: getWebServiceInfo().getOperations()) {
            String commandName;

            commandName = operation.getCommandClassName();

            sb.append("                <spring:prop key=\"{");
            sb.append(getWebServiceInfo().getCommandsNamespaceUri().toString());
            sb.append("}");
            sb.append(commandName);
            sb.append("\">\n                    ");
            
            if (operation.getReturns() == null) {
            	sb.append("Command");
            
            } else {
            	sb.append("ResponseCommand");
            }
            sb.append("Endpoint\n                </spring:prop>\n");
        }
        return sb.toString();
	}
	
	public String getExceptionMappings() {
        StringBuffer sb = new StringBuffer();
        
        for (WebServiceOperation operation
        		: getWebServiceInfo().getOperations()) {
        	
        	for (Class<? extends Throwable> exceptionType
        			: operation.getExceptions()) {
        		
        		if (!getWebServiceInfo().getExceptions()
        				.contains(exceptionType)) {
        			
        			getWebServiceInfo().getExceptions().add(exceptionType);
        			
        			sb.append("                <spring:prop key=\"");
					sb.append(exceptionType.getName());
					sb.append("\">\n");
					sb.append("                    CLIENT\n");
					sb.append("                </spring:prop>\n");
        		}
        	}
        }
        return sb.toString();
	}

	@Override
	public String getGeneratedFilename() {
		return "webapp/WEB-INF/" + getProjectName() + "-ws-servlet.xml";
	}
}
