package org.squeakytinkerings.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Replaces JSP-style tokens with methods from an object.
 * 
 * @author anton.beza
 *
 */
public final class ObjectTemplateInjector {

    /**
     * Instance of the replacer.
     */
    private String generatedPath;

    /**
     * Logger.
     */
    private static final Log LOGGER =
    	LogFactory.getLog(ObjectTemplateInjector.class);

    /**
     * Default constructor.
     */
    public ObjectTemplateInjector(String generatedPath) {
    	setGeneratedPath(generatedPath);
    }

    /**
     * Replace tokens in a template file with methods from the object.
     * @param obj
     * @param outputFilename
     */
    public void inject(ObjectTemplate obj) {
        BufferedReader br;
        BufferedWriter bw;
        final int tokenFromStartIndex = 3;
        final int tokenFromEndIndex = 2;
        
        try {

            br = new BufferedReader(new InputStreamReader(
            		obj.getClass().getClassLoader().getResourceAsStream(
                    		obj.getClass().getName() + ".t")));

            File outputFile = new File(
            		getGeneratedPath() + "/" + obj.getGeneratedFilename());

            // Create the necessary parent directories
            if (outputFile.getParentFile() != null
            		&& !outputFile.getParentFile().exists()) {
                LOGGER.info("Generating " + outputFile.getParentFile()
                        .getAbsolutePath());
                outputFile.getParentFile().mkdirs();
            }

            bw = new BufferedWriter(new FileWriter(outputFile));

            while (br.ready()) {

                String line = br.readLine();

                Pattern pattern = Pattern.compile("<%=[a-zA-Z0-9]*%>");
                Matcher matcher = pattern.matcher(line);

                StringBuffer sb = new StringBuffer();

                int i = 0;
                while (matcher.find()) {

                    String token = matcher.group();
                    
                    String name = token.substring(tokenFromStartIndex,
                            token.length() - tokenFromEndIndex);
                    
                    // Make the replacement using a method on the class
                    String replacement =
                    	(String) obj.getClass().getMethod(
                    			"get" + CamelCaseConverter
                    					.toUpperCamelCase(name)).invoke(obj);

                    sb.append(line.substring(i, matcher.start()));
                    if (replacement == null) {
                        sb.append(matcher.group(0));
                    } else {
                        sb.append(replacement);
                    }
                    i = matcher.end();
                }
                sb.append(line.substring(i, line.length()));
                bw.write(sb.toString() + "\n");
            }
            br.close();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @param generatedPath the generatedPath to set
	 */
	private void setGeneratedPath(String generatedPath) {
		this.generatedPath = generatedPath;
	}

	/**
	 * @return the generatedPath
	 */
	private String getGeneratedPath() {
		return generatedPath;
	}
}
