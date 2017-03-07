package myutils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

/**
 * Created by takayukihoshino on 1/16/17.
 */
public class NumString {

    String string;

    public NumString( BigInteger num ) {

        this.string = num.toString();

    }

    public NumString( String string ) {

        if ( ! StringUtils.isNumeric( string )  ) {
            throw new IllegalArgumentException( "Non-numeric charater in argument" );
        }
        this.string = string;

    }

    @Override
    public String toString() {
        return this.string;
    }

}
