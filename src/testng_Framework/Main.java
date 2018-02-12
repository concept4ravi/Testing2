package testng_Framework;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class Main {
	public static void main(String args[]) {
				
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG tng = new TestNG();
		List<String> suites = new ArrayList<String>();
		suites.add("/home/intern/eclipse-workspace/AmazonAlt/SignInTest.xml");
		tng.addListener((ITestNGListener)tla);;
		tng.setTestSuites(suites);
		tng.run();
	}
}
