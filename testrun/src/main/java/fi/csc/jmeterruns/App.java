package fi.csc.jmeterruns;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.SearchByClass;

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
    	JMeterUtils.loadJMeterProperties("/Users/klaalo/Downloads/apache-jmeter-2.13/bin/user.properties");
    	JMeterUtils.setJMeterHome("/Users/klaalo/Downloads/apache-jmeter-2.13/");
    	JMeterUtils.initLocale();
    	JMeterUtils.initLogging();

		SaveService.loadProperties();
		HashTree testPlanTree = SaveService.loadTree(new File("../shibbo-sp-initiated/shibbo-sp-initiated-Haka-test.jmx"));
			
		System.out.println(testPlanTree.toString());

		SearchByClass<HTTPSamplerProxy> s = new SearchByClass<HTTPSamplerProxy>(HTTPSamplerProxy.class);
		testPlanTree.traverse(s);
		Iterator<HTTPSamplerProxy> i = s.getSearchResults().iterator();
		HTTPSamplerProxy p = null;
		boolean cont = true;
		while (cont && i.hasNext()) {
			p = i.next();
			if (p.getName().equals("HTTP Request - Show AttributeViewer")) {
				System.out.println(p.getName());
				HashTree ht = s.getSubTree(p);
				for (Object o : ht.getArray()) {
					if (o.getClass().getName().equals("org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy") &&
							((HTTPSamplerProxy)o).getName().equals("HTTP Request - Show AttributeViewer")) {
						System.out.println(o.getClass().getName());
						System.out.println(((HTTPSamplerProxy)o).getName());
						break;
					}
				}
			}
		}
		if (p != null) {
			CustomTraverser ct = new CustomTraverser(p);
			testPlanTree.traverse(ct);
			System.out.println("add CustomTestListener");
			ct.getFound().add(new CustomTestListener());
			System.out.println(ct.getFound().toString());
		}
		
		System.out.println(testPlanTree.toString());

		jmeter.configure(testPlanTree);
		jmeter.run();
					
		
    }
        
}
