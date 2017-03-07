package junit; /**
 * Adapted from: https://www.tutorialspoint.com/junit/junit_api.htm
 * takayukihoshino on 2/12/17.
 */

import junit.framework.TestResult;
import junit.framework.TestSuite;

public class JunitTestSuite {

    public static void main( String[] a ) {

        // add the tests in the suite
        TestSuite suite = new TestSuite( TestJunit1.class,
                                         TestJunit2.class,
                                         TestJunit3.class );
        TestResult result = new TestResult();
        suite.run( result );
        System.out.println( "Number of test cases = " + result.runCount() );
    }

}
