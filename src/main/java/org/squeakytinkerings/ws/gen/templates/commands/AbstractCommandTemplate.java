package org.squeakytinkerings.ws.gen.templates.commands;

import org.squeakytinkerings.ws.gen.WebServiceElement;
import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.WebServiceOperation;
import org.squeakytinkerings.ws.gen.templates.WebServiceDocumentTemplate;


public abstract class AbstractCommandTemplate extends WebServiceDocumentTemplate {
	
	private WebServiceOperation operation;
	
	public AbstractCommandTemplate(
			WebServiceInfo webServiceInfo, WebServiceOperation operation) {
		super(webServiceInfo);
		setOperation(operation);
	}

	public abstract String getSuperClass();
	
	public String getOperationName() {
		return getOperation().getImplementationName();
	}
	
	public String getCommandClassName() {
		return getOperation().getCommandClassName();
	}
	
	public String getPropOrder() {
		StringBuffer sb = new StringBuffer();
		
		for (int parameterIndex = 0;
				parameterIndex < getOperation().getParameters().length;
				parameterIndex++) {
			
			if (parameterIndex > 0) {
				sb.append(", ");
			}
			
			sb.append("\"attr");
			sb.append(parameterIndex);
			sb.append("\"");
		}
		return sb.toString();
	}
	
	public String getOutputTypeClassName() {
		WebServiceElement returns = getOperation().getReturns();
		
		if (returns != null) {
			return returns.getImplementationType().getName();
		} else {
			return "void";
		}
	}
	
	public String getConstructorParameters() {
		StringBuffer sb = new StringBuffer();
		
		int parameterIndex = 0;
		
		for (WebServiceElement parameter : getOperation().getParameters()) {
			
			if (parameterIndex > 0) {
				sb.append(", ");
			}
			
			sb.append(parameter.getImplementationType().getName());
			sb.append(" arg");
			sb.append(parameterIndex);
		}
		return sb.toString();
	}
	
	public String getSetterCalls() {
		StringBuffer sb = new StringBuffer();
		
		for (int parameterIndex = 0;
				parameterIndex < getOperation().getParameters().length;
				parameterIndex++) {
			
			sb.append("\n        setAttribute");
			sb.append(parameterIndex);
			sb.append("(arg");
			sb.append(parameterIndex);
			sb.append(");");
		}
		return sb.toString();
	}
	
	public String getGettersAndSetters() {
		StringBuffer sb = new StringBuffer();
		
		int parameterIndex = 0;
		
		for (WebServiceElement parameter : getOperation().getParameters()) {
			
			// Output the attribute
			sb.append("\n\n    ");
			sb.append("@javax.xml.bind.annotation.XmlElement(");
			sb.append("name = ");
			sb.append(parameter.getXsdElementName());
			sb.append("namespace = ");
			sb.append(parameter.getXsdNamespaceUri().toString());
			sb.append(")\n    ");
			sb.append("private ");
			sb.append(parameter.getImplementationType().getName());
			sb.append(" attr");
			sb.append(parameterIndex);
			sb.append(";");
			
			// Output the setter definition
			sb.append("\n\n    public void setAttribute");
			sb.append(parameterIndex);
			sb.append("(");
			sb.append(parameter.getImplementationType().getName());
			sb.append(" value) {");
			sb.append("\n        attr");
			sb.append(parameterIndex);
			sb.append(" = value;");
			sb.append("\n    }");
			
			// Output the getter definition
			sb.append("\n\n    public ");
			sb.append(parameter.getImplementationType().getName());
			sb.append(" getAttribute");
			sb.append(parameterIndex);
			sb.append("() {");
			sb.append("\n        return attr");
			sb.append(parameterIndex);
			sb.append(";");
			sb.append("\n    }");
			
			parameterIndex += 1;
        }
		return sb.toString();
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(WebServiceOperation operation) {
		this.operation = operation;
	}

	/**
	 * @return the operation
	 */
	public WebServiceOperation getOperation() {
		return operation;
	}
}
