<?xml version="1.0"?>

<wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"

    xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
    
    xmlns:types="<%=typesNamespace%>"
    xmlns:commands="<%=commandsNamespace%>"
    
    xmlns:tns="<%=serviceNamespace%>"
    targetNamespace="<%=serviceNamespace%>">
    
    <wsdl:types>
        <xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema">
            <xsd:import namespace="<%=commandsNamespace%>"
                schemaLocation="../schemas/commands.xsd"/>
        </xsd:schema>
    </wsdl:types>
    
<%=messages%>
    <wsdl:portType name="<%=projectName%>PortType">
        
<%=operations%>
    </wsdl:portType>

    <wsdl:binding
        name="<%=projectName%>Binding"
        type="tns:<%=projectName%>PortType">
        
        <soap:binding
            style="document"
            transport="http://schemas.xmlsoap.org/soap/http"/>
            
<%=operationBindings%>
    </wsdl:binding>
    
    <wsdl:service name="<%=projectName%>">
    
        <wsdl:port name="<%=projectName%>Port"
            binding="tns:<%=projectName%>Binding">
            <soap:address location="/"/>
        </wsdl:port>
        
    </wsdl:service>

</wsdl:definitions>
