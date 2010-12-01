<?xml version="1.0" encoding="UTF-8"?>
<spring:beans xmlns:spring="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-2.0.xsd">

    
    <spring:bean id="<%=projectName%>"
        class="org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition">

        <spring:constructor-arg value="/WEB-INF/WSDL/<%=projectName%>.wsdl" />

    </spring:bean>

    <spring:bean id="messageFactory"
        class="org.springframework.ws.soap.saaj.SaajSoapMessageFactory" />

    <spring:bean id="payloadLoggingInterceptor"
        class="org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor">

        <spring:property name="logRequest" value="true" />
        <spring:property name="logResponse" value="true" />
        <spring:property name="loggerName" value="payloadMessages" />

    </spring:bean>

    <spring:bean id="soapEnvelopeLoggingInterceptor"
        class="org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor">

        <spring:property name="logRequest" value="true" />
        <spring:property name="logResponse" value="true" />
        <spring:property name="loggerName" value="soapEnvMessages" />

    </spring:bean>

    <spring:bean id="validatingInterceptor"
        class="org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor">

        <spring:property name="schema"
            value="/WEB-INF/schemas/commands.xsd" />
        <spring:property name="validateRequest" value="true" />
        <spring:property name="validateResponse" value="true" />

    </spring:bean>
          
    <spring:bean id="marshaller" class="org.springframework.oxm.jaxb.Jaxb2Marshaller">
        <spring:property
            name="contextPath"
            value="<%=beansPackageName%>:<%=commandsPackage%>"/>
    </spring:bean>

<%=operationEndpoints%>    <spring:bean id="endpointMapping"
        class="org.springframework.ws.server.endpoint.mapping.PayloadRootQNameEndpointMapping">

        <spring:property name="interceptors">
            <spring:list>
                <spring:ref bean="payloadLoggingInterceptor" />
                <spring:ref bean="soapEnvelopeLoggingInterceptor" />
                <spring:ref bean="validatingInterceptor" />
            </spring:list>
        </spring:property>

        <spring:property name="mappings">
            <spring:props>
<%=endpointMappings%>            </spring:props>
        </spring:property>

    </spring:bean>

    <spring:bean id="exceptionResolver"
        class="org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver">

        <spring:property name="defaultFault" value="SERVER" />
        <spring:property name="exceptionMappings">
            <spring:props>
<%=exceptionMappings%>            </spring:props>
        </spring:property>

    </spring:bean>

    <spring:bean id="messageDispatcher"
        class="org.springframework.ws.soap.server.SoapMessageDispatcher">

        <spring:property name="endpointMappings">
            <spring:list>
                <spring:ref bean="endpointMapping" />
            </spring:list>
        </spring:property>
        <spring:property name="endpointExceptionResolvers">
            <spring:list>
                <spring:ref bean="exceptionResolver" />
            </spring:list>
        </spring:property>

    </spring:bean>

</spring:beans>