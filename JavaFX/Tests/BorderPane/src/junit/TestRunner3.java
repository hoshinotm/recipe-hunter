package junit; /**
 * Adapted from: https://www.tutorialspoint.com/junit/junit_test_framework.htm
 * takayukihoshino on 2/10/17.
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner3 {

    public static void main( String[] args ) {
        Result result = JUnitCore.runClasses( TestJunit3.class );
        result.getFailures().forEach( f -> System.out.println( f ) );
        System.out.println( result.wasSuccessful() );
    }

}
