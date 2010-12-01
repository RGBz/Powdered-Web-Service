package org.squeakytinkerings.ws.gen;

import static org.junit.Assert.*;



import org.junit.Test;
import org.squeakytinkerings.ws.gen.AbstractWebServiceInfoFactory;
import org.squeakytinkerings.ws.gen.TopDownWebServiceGenerator;
import org.squeakytinkerings.ws.gen.WebServiceInfo;
import org.squeakytinkerings.ws.gen.impl.SimpleWebServiceInfoFactory;

public class TestTopDownWebServiceGenerator {

	@Test
	public void testGenerate() {
		
		AbstractWebServiceInfoFactory wsInfoFactory =
			new SimpleWebServiceInfoFactory();
		
		WebServiceInfo wsInfo;
		try {
			// TODO fix
			wsInfo = wsInfoFactory.generateWebServiceInfo("");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		TopDownWebServiceGenerator generator =
			new TopDownWebServiceGenerator(wsInfo);
		generator.generate();
		
		assertTrue(true);
	}
}
