package junit; /**
 * Adapted from: https://www.tutorialspoint.com/junit/junit_test_framework.htm
 * takayukihoshino on 2/8/17.
 */

import org.junit.Test;
import junit.*;

import static org.junit.Assert.*;

public class TestJunit1 {

    @Test
    public void testAdd() {

        // test data
        int num = 5;
        String temp = null;
        String str = "Junit is working fine";

        // check for equality
        assertEquals( "Junit is working fine", str );

        // check for false condition
        assertFalse( num > 6 );

        // check for null value
        assertNotNull( str );

    }

}