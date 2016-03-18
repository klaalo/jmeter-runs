package fi.csc.jmeterruns;

import java.util.Map;

import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.threads.JMeterContextService;

public class CustomTestListener implements TestStateListener {

	public void testEnded() {
		System.out.println("testEnded");
		Map<String, Object> sc = JMeterContextService.getContext().getSamplerContext();
		for (String str : sc.keySet()) {
			System.out.println(str);
		}
	}

	public void testEnded(String arg0) {
		System.out.println(arg0);
		
	}

	public void testStarted() {
		// do nothing
		
	}

	public void testStarted(String arg0) {
		System.out.println(arg0);
		
	}

}
