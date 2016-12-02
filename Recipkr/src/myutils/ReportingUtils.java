package myutils;

import java.lang.reflect.Method;

/**
 * Created by takayukihoshino on 11/9/16.
 */
public class ReportingUtils {

    public static void testingInfo( String string ) {
        System.err.println( string );
    }

    public Method getEnclosingMethodName() {
        return this.getClass().getEnclosingMethod();
    }

    public String getClassName() {
        return this.getClass().getName();
    }

}
