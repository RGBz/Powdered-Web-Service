package <%=commandsPackageName%>.client;

@javax.xml.bind.annotation.XmlAccessorType(
    javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(
    name = "",
    propOrder = {<%=propOrder%>})
@javax.xml.bind.annotation.XmlRootElement(
    name = "<%=commandClassName%>",
    namespace = "<%=commandsNamespace%>")
public class Client<%=commandClassName%>
extends <%=superClass%> {

<%=attributes%>
	
	public Client<%=commandClassName%>() {
    
    }
	
	public Client<%=commandClassName%>(<%=constructorParameters%>) {
<%=setterCalls%>
	}
	
<%=gettersAndSetters%>
}
