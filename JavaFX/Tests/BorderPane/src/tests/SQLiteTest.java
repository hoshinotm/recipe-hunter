package tests;

import java.sql.Connection;
import java.util.Scanner;

import authentication.Digest;
import core.CoreSettings;
import database.UserDataAccess;
import database.DataValidation;

/**
 * Created by takayukihoshino on 1/29/17.
 */
public class SQLiteTest {

    private static String dbName;
    private static String dbLocation;
    private static final String DATABASE_NAME = "TestUsersDB.db";
    private static final String DATABASE_LOCATION = "/Develop/Workspace/Java/JavaFX/Tests/BorderPane/db/";
    private static final int TEST_USER_ID = 1;
    private static final String TEST_USERNAME = "test-user";
    private static final String TEST_USER_EMAIL = "hoshinot@gmail.com";
    private static final String TEST_USER_PHONE = "+1-773-425-2824";
    private static final String TEST_USER_PASSWORD = "?13!SipqY76$";

    public static void main ( String[] args ) {

        dbName = DATABASE_NAME;
        dbLocation = DATABASE_LOCATION;
        // Create a db
        UserDataAccess db = new UserDataAccess( dbLocation, dbName );
        Connection connection =  db.openConnection();
        db.createUserTable();
        // Try inserting a new user
        // db.specifyNewUsersParameters( TEST_PHONE_NUMBER );
        int userID =
                db.addNewUser( TEST_USERNAME, TEST_USER_EMAIL, TEST_USER_PHONE,
                        new Digest( TEST_USERNAME, TEST_USER_PASSWORD) );
        System.out.println( db.getUserDataForUserID( userID ) );
        // TODO Try deleting the table
        // Close the db connection
        db.closeUserDatabase();

    }

    private void testPhoneNumberValidation() {

        Scanner in = new Scanner( System.in );
        String number;
        for (;;) {
            System.out.print( "> " );
            if ( (number = in.nextLine()).equals( "" ) ) {
                break;
            }
            System.out.println( number +
                    ( DataValidation.isValidPhoneNumberFormat( number )  ? " is " : " is not " ) +
                    "a valid phone number." );
        }

    }

}
