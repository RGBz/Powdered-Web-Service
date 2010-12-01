<?xml version="1.0" encoding="UTF-8"?>
<spring:beans
    xmlns:spring="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-2.0.xsd">
        
    <spring:bean id="marshaller" class="org.springframework.oxm.jaxb.Jaxb2Marshaller"
          p:contextPath="<%=beansPackageName%>:<%=commandsPackage%>.client" />
    
    <spring:bean id="messageFactory" class="org.springframework.ws.soap.saaj.SaajSoapMessageFactory"/>
        
    <spring:bean id="webServiceTemplate" class="org.springframework.ws.client.core.WebServiceTemplate">
        
        <spring:constructor-arg ref="messageFactory"/>
        
        <spring:property name="defaultUri"
            value="<%=defaultUrl%>"/>
        
        <spring:property name="marshaller" ref="marshaller"/>
        <spring:property name="unmarshaller" ref="marshaller"/>
    
    </spring:bean>
    
</spring:beans>
