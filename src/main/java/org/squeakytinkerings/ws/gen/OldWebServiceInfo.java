package org.squeakytinkerings.ws.gen;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchema;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


public class OldWebServiceInfo {

	private String projectName;
	
	private Class<?> serviceImplementationClass;

	private Set<WebServiceOperation> serviceOperations;
	
	private String typesNamespace;

	private Set<Class<?>> exceptions;
	
	private Set<WebServiceOperation> loadedOperations;
	
	private MarshallerChoice marshallerChoice;
	
	private static final Set<String> IGNORABLE_METHOD_NAMES;
	
	private static final String XSD_ELEMENT_IMPL_TYPE_MAPPING_PATH = 
		"element-to-impl.mapping";
	
	static {
		
		IGNORABLE_METHOD_NAMES = new HashSet<String>();
		for (Method objectMethod : Object.class.getMethods()) {
			IGNORABLE_METHOD_NAMES.add(objectMethod.getName());
		}
	}
	
	public OldWebServiceInfo(
			String projectName,
			String generatedDirectoryPath,
			String serviceImplementationClassName) {
		initialize(projectName, generatedDirectoryPath,
				serviceImplementationClassName);
	}
	
	private void loadElementToImplementationMapping() {
		
		if (new File(XSD_ELEMENT_IMPL_TYPE_MAPPING_PATH).exists()) {
			ClassPathResource resource =
				new ClassPathResource(XSD_ELEMENT_IMPL_TYPE_MAPPING_PATH);
			XmlBeanFactory factory = new XmlBeanFactory(resource);
			
			loadedOperations =
				(Set<WebServiceOperation>) factory.getBean("operations");
		
		} else {
			loadedOperations = new HashSet<WebServiceOperation>();
		}
	}
	
	private void saveElementToImplementationMapping() {
		ClassPathResource resource =
			new ClassPathResource(XSD_ELEMENT_IMPL_TYPE_MAPPING_PATH);
		XmlBeanFactory factory = new XmlBeanFactory(resource);
		
	}
	
	/**
	 * Generate a command class name from an operation name.
	 * @param operationName
	 * @return
	 */
	public String generateCommandClassName(String operationName) {
		return operationName.substring(0, 1).toUpperCase()
			+ operationName.substring(1) + "Command";
	}
	
	private void initialize(
		String projectName,
		String generatedDirectoryPath,
		String serviceImplementationClassName) {
			
		try {
						
			setProjectName(projectName);
			
			setServiceImplementationClass(
					Class.forName(serviceImplementationClassName));
						
			setTypesNamespace(Class.forName(getBeansPackage().getName()
				+ ".package-info").getAnnotation(XmlSchema.class).namespace());
			
			operationSetup();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	private void operationSetup() {
		
		setOperations(new HashSet<WebServiceOperation>());
		setExceptions(new HashSet<Class<?>>());
		
		// Load up the mapping
		loadElementToImplementationMapping();
		
		// Go through the operations listed in the current service class, if
		// the operation signature exactly matches an operation in the loaded
		// file, simply remove the operation from the loaded file list
		
		// If the signature does not match, try and assign element names to the
		// return types and parameter types (ask the programmer when more than
		// one possibility exists
		
		// To do this, load up all of the root elements from the types schema
		// and ask the programmer questions on root elements that are either
		// simpleTypes or share a common complexType
		
		// Also add in exceptions
		
		for (Method method : getServiceImplementationClass().getMethods()) {
			
			// Make sure these methods aren't on the Object base class
			if (!IGNORABLE_METHOD_NAMES.contains(method.getName())) {
				System.out.println(
						"Adding operation \"" + method.getName() + "\".");
				
				WebServiceOperation operation = new WebServiceOperation();
				
				operation.setImplementationName(method.getName());
				
				for (Class<?> parameter : method.getParameterTypes()) {

					if (parameter.getAnnotation(XmlElement.class) != null) {
						
						
					}
				}
			}
		}
	}
	
	private void setExceptions(Set<Class<?>> set) {
		exceptions = set;
	}

	/**
	 * @param projectName the projectName to set
	 */
	private void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	private void setOperations(HashSet<WebServiceOperation> hashSet) {
		this.serviceOperations = hashSet;
	}

	private void setTypesNamespace(String typesNamespace)
		throws ClassNotFoundException {
		this.typesNamespace = typesNamespace;
	}

	/**
	 * @param serviceImplementationClass the serviceImplementationClass to set
	 */
	private void setServiceImplementationClass(
			Class<?> serviceImplementationClass) {
		this.serviceImplementationClass = serviceImplementationClass;
	}

	public String getDefaultUri() {
		return "http://localhost:8080/" + getProjectName();
	}

	public String getRootPackageName() {
		String beansPackageName = getBeansPackage().getName();
		return beansPackageName.substring(0,
				beansPackageName.lastIndexOf("."));
	}
	
	public String getTypesNamespace() {
		return typesNamespace;
	}
	
	public String getCommandsNamespace() {
		return getTypesNamespace() + "/commands";
	}
	
	public String getServiceNamespace() {
		return getTypesNamespace() + "/service";
	}

	/**
	 * Get the commands package name.
	 * @return
	 */
	public String getCommandsPackageName() {
		return getRootPackageName() + ".commands";
	}

	/**
	 * @return the beansPackage
	 */
	public Package getBeansPackage() {
		return getServiceImplementationClass().getMethods()[0]
                .getParameterTypes()[0].getPackage();
	}

	public Set<WebServiceOperation> getOperations() {
		return serviceOperations;
	}

	/**
	 * @return the serviceImplementationClass
	 */
	public Class<?> getServiceImplementationClass() {
		return serviceImplementationClass;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	public Set<Class<?>> getExceptions() {
		return exceptions;
	}

	/**
	 * @param loadedOperations the loadedOperations to set
	 */
	public void setLoadedOperations(Set<WebServiceOperation> loadedOperations) {
		this.loadedOperations = loadedOperations;
	}

	/**
	 * @return the loadedOperations
	 */
	public Set<WebServiceOperation> getLoadedOperations() {
		return loadedOperations;
	}
}
