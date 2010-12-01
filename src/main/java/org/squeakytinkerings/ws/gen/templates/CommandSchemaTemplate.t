<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
    
    xmlns:types="<%=typesNamespace%>"
    
    xmlns:tns="<%=commandsNamespace%>"
    targetNamespace="<%=commandsNamespace%>"
    
    elementFormDefault="qualified"
    attributeFormDefault="unqualified">

    <xs:import namespace="<%=typesNamespace%>"
        schemaLocation="types.xsd" />

<%=commandElements%>
</xs:schema>