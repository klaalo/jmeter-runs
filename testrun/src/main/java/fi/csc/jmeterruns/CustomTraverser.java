package fi.csc.jmeterruns;

import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.HashTreeTraverser;

public class CustomTraverser implements HashTreeTraverser {

	HashTree foundTree;
	HTTPSamplerProxy findThis;
	
	public CustomTraverser (HTTPSamplerProxy p) {
		findThis = p;
	}
	
	public void addNode(Object arg0, HashTree arg1) {
		//System.out.println(arg0.getClass().getName());
		if (arg0.equals(findThis)) {
			foundTree = arg1;
		}
	}

	public void processPath() {
		// do nothing
		
	}

	public void subtractNode() {
		// do nothing
		
	}
	
	public HashTree getFound() {
		return foundTree;
	}

}
