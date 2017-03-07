/**
 * Created by takayukihoshino on 1/29/17.
 */

package database;

public enum UserStatus {

    UNDEFINED ( 0 ),
    UNVERIFIED( 1 ),
    ENABLED( 2 ),
    DISABLED( 3 ),
    DEACTIVATED( 4 );

    private int value;

    UserStatus ( int value ) {
        this.value = value;
    }

    public static UserStatus getUserStatusForValue( int value ) {

        UserStatus status;
        if ( value == UNDEFINED.ordinal() ) {
            status = UNDEFINED;
        } else if ( value == UNVERIFIED.ordinal() ) {
            status = UNVERIFIED;
        } else if ( value == ENABLED.ordinal() ) {
            status = ENABLED;
        } else if ( value == DISABLED.ordinal() ) {
            status = DISABLED;
        } else if ( value == DEACTIVATED.ordinal() ) {
            status = DEACTIVATED;
        } else {
            status = null;
        }
        return status;
    }

    public int getValue() {
        return this.value;
    }



}
