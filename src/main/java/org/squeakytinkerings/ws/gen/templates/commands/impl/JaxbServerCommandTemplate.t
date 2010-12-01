package <%=commandsPackageName%>;

@javax.xml.bind.annotation.XmlAccessorType(
    javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(
    name = "",
    propOrder = {<%=propOrder%>})
@javax.xml.bind.annotation.XmlRootElement(
    name = "<%=commandClassName%>",
    namespace = "<%=commandsNamespace%>")
public class <%=commandClassName%>
implements <%=superClass%> {

<%=attributes%>
	
	public <%=commandClassName%>() {
	
	}
	
	public <%=commandClassName%>(<%=parameters%>) {
<%=setterCalls%>
	}
	
	public <%=operationReturnType%> execute() throws Exception {
	    
	    <%=serviceImplementationFullyQualifiedClassName%> operations = 
	            new <%=serviceImplementationFullyQualifiedClassName%>();
		
		operations.<%=operationName%>(<%=operationArguments%>);
	}
	
<%=gettersAndSetters%>
}
