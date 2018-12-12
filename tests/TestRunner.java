import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
//import junit.runner.Version;


public class TestRunner {

	public static void main(String[] args) {
		//System.out.println("JUnit version is: " + Version.id());
		Result result = JUnitCore.runClasses(MHSTestSuite.class);
		for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
		System.out.println("Number of tests in this run: " + result.getRunCount() + "\nFailures: " + result.getFailureCount());

	}

}
