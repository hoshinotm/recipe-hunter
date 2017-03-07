package junit;

import junit.framework.AssertionFailedError;
import junit.framework.TestResult;

/**
 * Adapted from: https://www.tutorialspoint.com/junit/junit_test_framework.htm
 * takayukihoshino on 2/10/17.
 */

public class TestJunit3 extends TestResult {

    // add the error
    public synchronized void addError( org.junit.Test test, Throwable throwable ) {
        super.addError( (junit.framework.Test)test, throwable );
    }

    // add the failure
    public synchronized void addFailure( org.junit.Test test, AssertionFailedError assertionFailedError ) {
        super.addFailure( (junit.framework.Test)test, assertionFailedError );
    }

    @org.junit.Test
    public void testAdd() {
        // add any test here
    }

    // marks that the test run should stop
    public synchronized void stop() {
        // stop test here
    }

}
