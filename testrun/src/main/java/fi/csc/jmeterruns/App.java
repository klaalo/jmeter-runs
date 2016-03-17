package fi.csc.jmeterruns;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	StandardJMeterEngine jmeter = new StandardJMeterEngine();
    	
    	JMeterUtils.loadJMeterProperties("/Users/klaalo/Downloads/apache-jmeter-2.13/bin/jmeter.properties");
    	JMeterUtils.setJMeterHome("/Users/klaalo/Downloads/apache-jmeter-2.13/");
    	JMeterUtils.initLocale();
    	
		SaveService.loadProperties();
		FileInputStream in = new FileInputStream("../shibbo-sp-initiated/shibbo-sp-initiated-Haka-test.jmx");
		HashTree testPlanTree = SaveService.loadTree(in);
		in.close();
		
		jmeter.configure(testPlanTree);
		jmeter.run();
    	
    }
}
