package junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Adapted from: https://www.tutorialspoint.com/junit/junit_api.htm
 * by takayukihoshino on 2/9/17.
 */
public class TestRunner2 {

    public static void main( String[] args ) {

        Result result = JUnitCore.runClasses( TestJunit2.class );

        result.getFailures().forEach( f -> System.out.println(f) );

        System.out.println( result.wasSuccessful() );
    }

}
