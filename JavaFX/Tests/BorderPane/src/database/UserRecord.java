/**
 * Created by takayukihoshino on 1/29/17.
 */

package database;

import authentication.Digest;
import java.time.DateTimeException;
import java.time.OffsetDateTime;

public class UserRecord {

    private int userID;
    private String userName;
    private UserStatus status;
    private Digest digest;
    private String email;
    private String phoneNumber;
    private OffsetDateTime dateTimeCreated;
    private OffsetDateTime dateTimeModified;
    private OffsetDateTime dateTimeAccessed;
    private String phoneNumberRegex;
    // Valid phone numbers are: 9999999999, 1-999-999-9999 or 999-999-9999
    static final String defaultPhoneNumberRegex =
            "^\\+?1?\\-?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";

    public UserRecord() {

        this.userID = -1;
        this.userName = "";
        this.status = UserStatus.UNDEFINED;
        this.digest = new Digest( "", "" );
        this.email = "";
        this.phoneNumber = "";
        this.phoneNumberRegex = defaultPhoneNumberRegex;
    }

    public UserRecord( int userID, String userName,
                       Digest digest, UserStatus status,
                       String email, String phoneNumber ) {

        if ( !DataValidation.isValidPhoneNumberFormat( phoneNumber ) ) {
            throw new IllegalArgumentException( "Invalid phone number string '" +
                    phoneNumber + "'" );
        } else if ( !DataValidation.isValidEmailAddressFormat( email ) ) {
            throw new IllegalArgumentException( "Invalid email address string '" +
                    email + "'" );
        }
        this.userID = userID;
        this.userName = userName;
        this.digest = digest;
        this.status = status;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.phoneNumberRegex = defaultPhoneNumberRegex;
    }

    public int getUserID() {

        return userID;
    }

    public void setUserID( int userID ) {

        this.userID = userID;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName( String userName ) {

        this.userName = userName;
    }

    public Digest getDigest() {

        return digest;
    }

    public void setDigest( Digest digest ) {

        this.digest = digest;
    }

    public UserStatus getStatus() {

        return status;
    }

    public void setStatus( UserStatus status ) {

        this.status = status;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {

        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail( String email ) {

        this.email = email;
    }

    public String getPhoneNumberRegex() {

        return phoneNumberRegex;
    }

    public void setPhoneNumberRegex( String phoneNumberRegex ) {

        this.phoneNumberRegex = phoneNumberRegex;
    }

    public OffsetDateTime getDateTimeCreated() {

        return dateTimeCreated;
    }

    public void setDateTimeCreated( String dateTimeCreated ) {

        try {
            this.dateTimeCreated = OffsetDateTime.parse( dateTimeCreated );
        } catch ( DateTimeException e ) {
            System.err.println(
                    getClass() + ".setDateTimeCreated(): Invalid OffsetDateTime string " +
                    dateTimeCreated );
            e.printStackTrace();
        }
    }

    public void setDateTimeCreated( OffsetDateTime dateTimeCreated ) {

        this.dateTimeCreated = dateTimeCreated;
    }

    public OffsetDateTime getDateTimeModified() {

        return dateTimeModified;
    }

    public void setDateTimeModified( String dateTimeModified ) {

        try {
            this.dateTimeModified = OffsetDateTime.parse( dateTimeModified );
        } catch ( DateTimeException e ) {
            System.err.println(
                    getClass() + ".setDateTimeModified(): Invalid OffsetDateTime string " +
                            dateTimeModified );
            e.printStackTrace();
        }
    }

    public void setDateTimeModified( OffsetDateTime dateTimeModified ) {

        this.dateTimeModified = dateTimeModified;
    }

    public OffsetDateTime getDateTimeAccessed() {

        return dateTimeAccessed;
    }

    public void setDateTimeAccessed( String dateTimeAccessed ) {

        try {
            this.dateTimeAccessed = OffsetDateTime.parse( dateTimeAccessed );
        } catch ( DateTimeException e ) {
            System.err.println(
                    getClass() + ".setDateTimeAccessed(): Invalid OffsetDateTime string " +
                            dateTimeAccessed );
            e.printStackTrace();
        }
    }

    public void setDateTimeAccessed( OffsetDateTime dateTimeAccessed ) {

        this.dateTimeAccessed = dateTimeAccessed;
    }

    @Override
    public String toString() {
        final String format =
                "User ID: %s/ Username: %s/ Status: %d/ Email: %s/ Phone: %s/ Created: %s/ Modified: %s/ Accessed: %s";
        return String.format( format, getUserID(), getUserName(),
                getStatus().getValue(), getEmail(), getPhoneNumber(),
                getDateTimeCreated(), getDateTimeModified(), getDateTimeAccessed() );

    }
}
