/**
 * Created by takayukihoshino on 3/2/17.
 */

package database;

import org.apache.commons.validator.routines.EmailValidator;
import java.util.regex.Pattern;

public class DataValidation {

    // Valid phone numbers are: [+][1]9999999999, [+][1-]999-999-9999 and
    // [+][1-]999-999-9999.
    private static final String validPhoneNumberPattern =
            "^\\+?1?\\-?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";

    public DataValidation() {

    }

    public static String removeAnyLeadingPlusSign( String string ) {

        if ( string.charAt( 0 ) == '+' ) {
            string = string.substring( 1, string.length() );
        }
        return string;
    }

    public static boolean isValidPhoneNumberFormat( String string ) {

        return Pattern.compile( validPhoneNumberPattern ).matcher( string ).matches();
    }

    public static boolean isValidEmailAddressFormat( String string ) {

        return EmailValidator.getInstance().isValid( string );
    }

}
