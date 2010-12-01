package org.squeakytinkerings.ws.gen.templates;

import org.squeakytinkerings.ws.gen.WebServiceElement;
import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;

public class CommandSchemaTemplate extends WebServiceDocumentTemplate {
	
	public CommandSchemaTemplate(WebServiceInfo webServiceInfo) {
		super(webServiceInfo);
	}
        
    public String getCommandElements() {
    	StringBuffer sb = new StringBuffer();
    	
        for (WebServiceOperation operation
        		: getWebServiceInfo().getOperations()) {
        	String commandName;
        	String inputElementName;
        	
        	commandName = operation.getCommandClassName();
        	
        	for (WebServiceElement parameter : operation.getParameters()) {
        		
	        	inputElementName = parameter.getXsdElementName();
	        	
		        sb.append("    <xs:element name=\"");
		        sb.append(commandName);
		        sb.append("\">\n");
		        sb.append("        <xs:complexType><xs:sequence>\n");
		        sb.append("            <xs:element ref=\"types:");
		        sb.append(inputElementName);
		        sb.append("\"/>\n");
		        sb.append("        </xs:sequence></xs:complexType>\n");
		        sb.append("    </xs:element>\n\n");
        	}
        }
    	return sb.toString();
	}

	@Override
	public String getGeneratedFilename() {
		return "webapp/WEB-INF/schemas/commands.xsd";
	}
}
