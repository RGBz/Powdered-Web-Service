package org.squeakytinkerings.ws.gen.templates;

import javax.xml.bind.annotation.XmlRootElement;

import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;


public class WsdlTemplate extends WebServiceDocumentTemplate {

	public WsdlTemplate(WebServiceInfo webServiceInfo) {
		super(webServiceInfo);
	}

	public String getMessages() {
		StringBuffer sb = new StringBuffer();
		
        for (WebServiceOperation operation
        		: getWebServiceInfo().getOperations()) {
            String operationName;
            String commandName;
            String responseName;

            operationName = operation.getImplementationName();
            commandName = operation.getCommandClassName();
            
            System.out.println(operation.getImplementationName());
            System.out.println(operation.getReturns()
            		.getImplementationType().getName());
            
            responseName = operation.getReturns().getImplementationType()
            		.getAnnotation(XmlRootElement.class).name();
            
            sb.append("    <wsdl:message name=\"");
            sb.append(operationName);
            sb.append("Request\">\n");
            sb.append("        <wsdl:part name=\"body\" element=\"commands:");
            sb.append(commandName);
            sb.append("\"/>\n");
            sb.append("    </wsdl:message>\n");

            sb.append("    <wsdl:message name=\"");
            sb.append(operationName);
            sb.append("Response\">\n");
            sb.append("        <wsdl:part name=\"body\" element=\"types:");
            sb.append(responseName);
            sb.append("\"/>\n");
            sb.append("    </wsdl:message>\n");
        }
        return sb.toString();
	}
	
	public String getOperations() {
		StringBuffer sb = new StringBuffer();
        
        for (WebServiceOperation operation
        		: getWebServiceInfo().getOperations()) {
            String operationName;

            operationName = operation.getImplementationName();
            
            sb.append("        <wsdl:operation name=\"");
            sb.append(operationName);
            sb.append("\">\n");
            sb.append("            <wsdl:input message=\"tns:");
            sb.append(operationName);
            sb.append("Request\"/>\n");
            sb.append("            <wsdl:output message=\"tns:");
            sb.append(operationName);
            sb.append("Response\"/>\n");
            sb.append("        </wsdl:operation>\n");
        }
        return sb.toString();
	}
	
	public String getOperationBindings() {
		StringBuffer sb = new StringBuffer();
        
        for (WebServiceOperation operation
        		: getWebServiceInfo().getOperations()) {
            String operationName;

            operationName = operation.getImplementationName();

            sb.append("        <wsdl:operation name=\"");
            sb.append(operationName);
            sb.append("\">\n");
            sb.append("            <wsdl:input>\n");
            sb.append("                <soap:body parts=\"body\" "
                    + "use=\"literal\"/>\n");
            sb.append("            </wsdl:input>\n");
            sb.append("            <wsdl:output>\n");
            sb.append("                <soap:body parts=\"body\" "
                    + "use=\"literal\"/>\n");
            sb.append("            </wsdl:output>\n");
            sb.append("        </wsdl:operation>\n");
        }
        return sb.toString();
	}

	@Override
	public String getGeneratedFilename() {
		return "webapp/WEB-INF/WSDL/" + getProjectName() + ".wsdl";
	}
}
