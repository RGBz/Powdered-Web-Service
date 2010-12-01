package org.squeakytinkerings.ws.gen;

import org.squeakytinkerings.util.ObjectTemplateInjector;
import org.squeakytinkerings.ws.gen.templates.commands.impl.JaxbCommandClassGenerator;


public class CommandClassGeneratorFactory {

	private static CommandClassGeneratorFactory instance;
	
	private CommandClassGeneratorFactory() {
		
	}

	public static CommandClassGeneratorFactory getInstance() {
		if (instance == null) {
			instance = new CommandClassGeneratorFactory();
		}
		return instance;
	}
	
	public AbstractCommandClassGenerator getCommandClassGenerator(
			WebServiceInfo wsInfo, ObjectTemplateInjector injector) {
		
		// TODO remove hard-coding
		return new JaxbCommandClassGenerator(wsInfo, injector);
	}
}
