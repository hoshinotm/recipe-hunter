package myutils;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;


/**
 * Created by takayukihoshino on 1/16/17.
 */
public class PhoneNumber {

    private String country;
    private String domestic;

    public PhoneNumber( String  number ) {

        number = number.replace( "-", "" );
        number = number.replace( " ", "" );
        if ( ! StringUtils.isNumeric( number ) ) {
            throw new IllegalArgumentException(
                    "Argument '" + number + "' contains a non-numeric character" );
        }
        this.country = getCountryCode( Locale.US.getCountry() );
    }

    public PhoneNumber( String country, String domestic ) {

        country = country.replace( "-", "" );
        country = country.replace( " ", "" );
        domestic = domestic.replace( "-", "" );
        domestic = domestic.replace( " ", "" );
        if ( ! StringUtils.isNumeric( country ) || ! StringUtils.isNumeric( domestic ) ) {
            throw new IllegalArgumentException(
                    "At least one argument contains a non-numeric character" );
        }
    }

    private String getCountryCode( String countryCodeString ) {

        String countryCode;
        // Accept US only for now
        if ( countryCodeString.toUpperCase().contains( "US" ) ) {
            countryCode = "1";
        } else {
            countryCode = "";
        }
        return countryCode;
    }

    //getter, comparator, etc...

    public String getCountry() {

        return country;
    }

    public void setCountry( String country ) {

        this.country = country;
    }

    public String getDomestic() {

        return domestic;
    }

    public void setDomestic( String domestic ) {

        this.domestic = domestic;
    }

}
