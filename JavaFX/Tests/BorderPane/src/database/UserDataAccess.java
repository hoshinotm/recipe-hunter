package database;

import authentication.Digest;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Formatter;

/**
 * Created by takayukihoshino on 1/29/17.
 */
public class UserDataAccess extends JDBC {

    static final String createUserTable =
            "CREATE TABLE USER " +
                    "(USERID INT PRIMARY KEY     NOT NULL," +
                    " USERNAME       TEXT    NOT NULL," +
                    " AUTH           TEXT    NOT NULL," +
                    " STATUS         INT     NOT NULL," +
                    " EMAIL          TEXT    NOT NULL," +
                    " PHONE          TEXT    NOT NULL," +
                    " CREATED        TEXT             ," +
                    " MODIFIED       TEXT             ," +
                    " ACCESSED       TEXT              )";
    static String insertNewUser =
            "INSERT INTO USER (USERID, USERNAME, AUTH, STATUS, EMAIL, PHONE, CREATED, MODIFIED, ACCESSED) " +
                    "VALUES ( %d, '%s', '%s', 0, '%s', '%s', '%s', '%s', '%s' );";

    private int nextUserID = 1;

    public UserDataAccess( String dbLocation, String dbName ) {

        super( dbLocation, dbName );
    }

    /***
     * createDBTable()
     *
     * execute the createSQL script to create a database table, using
     * the open connection passed through the parameter.
     */
    public void createUserTable() {

        super.executeDBUpdate( createUserTable );
    }


    public int addNewUser( String userName, String email, String phoneNumber, Digest digest ) {

        String insertCmd =
                specifyNewUsersParameters( this.nextUserID, userName, digest,
                                            email, phoneNumber );
        super.executeDBUpdate( insertCmd );
        return this.nextUserID++;
    }

    public void addNewUser( UserRecord userRecord ) {

        String insertCmd =
                specifyNewUsersParameters( userRecord.getUserID(),
                                            userRecord.getUserName(),
                                            userRecord.getDigest(),
                                            userRecord.getEmail(),
                                            userRecord.getPhoneNumber() );
        super.executeDBUpdate( insertCmd );
    }
    
    public UserRecord getUserDataForUserID( int userID ) {

        String selectAUser = String.format(
                "SELECT * FROM USER WHERE USERID = %d;", userID );
        CachedRowSet rowSet = super.executeDBQuery( selectAUser );
        if ( rowSet.size() > 1 ) {
            String reason =
                    String.format( "Multiple records for user ID " + userID );
            handleException(
                    new SQLDataException( reason ));
        }
        return extractUserRecord( rowSet );
    }

    public UserRecord extractUserRecord( CachedRowSet rowSet ) {

        UserRecord userRecord = new UserRecord();
        try {
            if ( rowSet.next() ) {
                userRecord.setUserID( rowSet.getInt( "USERID" ) );
                userRecord.setUserName( rowSet.getString( "USERNAME" ) );
                // TODO Implemnet
                userRecord.setDigest( null );
                int statusValue = rowSet.getInt( "STATUS" );
                userRecord.setStatus( UserStatus.getUserStatusForValue( statusValue ) );
                userRecord.setEmail( rowSet.getString( "EMAIL") );
                userRecord.setPhoneNumber( rowSet.getString( "PHONE" ) );
                userRecord.setDateTimeCreated( rowSet.getString( "CREATED" ) );
                userRecord.setDateTimeModified( rowSet.getString( "MODIFIED" ) );
                userRecord.setDateTimeAccessed( rowSet.getString( "ACCESSED" ) );
            }
        } catch ( SQLException e ) {
            handleException( e );
        }
        return userRecord;
    }

    public UserRecord getUserDataForUsernameAndDigest( String username, Digest digest ) {

        String verifyAUser = String.format(
                "SELECT * FROM USER WHERE USERNAME = %s AND AUTH = %s;", username, digest );
        CachedRowSet rowSet = super.executeDBQuery( verifyAUser );
        if ( rowSet.size() > 1 ) {
            String reason =
                    String.format( "Multiple records for username '%s' and digest '%s'" +
                            username, digest );
            handleException(
                    new SQLDataException( reason ));
        }
        return extractUserRecord( rowSet );
    }

    public boolean updateUserData( UserRecord userRecord ) {

        return false;
    }

    public boolean activateUser( int userID ) {

        return false;
    }

    public boolean deactivateUser( int userID ) {

        return false;
    }

    public String specifyNewUsersParameters( int userID, String username,
                                             Digest digest,
                                             String email, String phoneNumber ) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter( sb );
        int userStatus = UserStatus.UNDEFINED.getValue();
        String createDateTime = OffsetDateTime.now().toString();
        String modifiedDateTime = createDateTime;
        String accessedDateTime = createDateTime;

        formatter.format( insertNewUser, userID, username, digest,
                email, phoneNumber, createDateTime, modifiedDateTime, accessedDateTime );
        return sb.toString();
    }


}
