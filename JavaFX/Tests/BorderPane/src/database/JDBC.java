
/**
 * Adapted from https://www.tutorialspoint.com/sqlite/sqlite_java.htm
 *
 */

package database;

import com.sun.rowset.CachedRowSetImpl;
import core.CoreSettings;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;

public class JDBC {

    private String dbName;
    private String dbLocation;

    private Connection connection;

    // TODO Implement own AutoCloseable
    private ArrayList<AutoCloseable> closeableResources;

    private static final boolean AUTOCOMMIT_DEFAULT = true;

    public JDBC( String dbLocation, String dbName ) {

        this.dbLocation = dbLocation;
        this.dbName = dbName;
        this.closeableResources = new ArrayList<>();
    }

    public Connection openConnection() {

        return openConnection( AUTOCOMMIT_DEFAULT );
    }

    public Connection openConnection( boolean enableAutoCommit ) {

        Connection connection = null;
        try {
            Class.forName( "org.sqlite.JDBC" ); // Load database driver
            String dbURL = getDBURL( this.dbName );
            System.out.println( "dbURL = " + dbURL );
            connection = DriverManager.getConnection( dbURL );
            connection.setAutoCommit( enableAutoCommit );
            System.out.println( "Opened database successfully" );
        } catch ( Exception e ) {
            handleException( e );
        }
        this.connection = connection;
        return connection;
    }

    public void deleteDBTable( ) {

        // TODO Implement method
        assert( false );
    }

    public void closeUserDatabase() {

        try {
            this.connection.close();
        } catch ( SQLException e ) {
            handleException( e );
        }
    }

    public void executeDBUpdate( String script ) {

        try {
            System.out.println( getClass() + ".executeDBUpdate: " +
                    "Executing " + script );
            Statement statement = this.connection.createStatement();
            statement.executeUpdate( script );
            statement.close();
        } catch ( SQLException e ) {
            handleException( e );
        }
    }

    public CachedRowSet executeDBQuery( String script ) {

       CachedRowSet rowSet = null;
       try {
            System.out.println( getClass() + ".executeDBQuery: " +
                    "Executing " + script );
            Statement statement = this.connection.createStatement();
            rowSet = new CachedRowSetImpl();
            rowSet.populate( statement.executeQuery( script ) );
            // addToResourceToBeReleased( result );
            statement.close();
        } catch ( SQLException e ) {
            handleException( e );
        }
        return rowSet;
    }

    public Connection getConnection() {

        return connection;
    }

    public void setConnection( Connection connection ) {

        this.connection = connection;
    }

    public boolean isActive() {

        boolean active = false;

        try {
            active = connection.isValid( CoreSettings.DB_CONNECT_TIMEOUT_SECS );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return active;
    }

    private String getDBURL( String dbName ) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter( sb );
        formatter.format( "jdbc:sqlite:%s:%s", dbLocation, dbName );
        return sb.toString();
    }

    public String getDbName() {

        return dbName;
    }

    public String getDbLocation() {

        return dbLocation;
    }

    public void handleException( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        e.printStackTrace();
    }

    private void addToResourceToBeReleased( AutoCloseable closeable ) {
        this.closeableResources.add( closeable );
    }

}

    /*

} catch ( Exception e ) {
    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    System.exit(0);
}
        System.out.println("Table created successfully");



        else {

            [0] == .contains([+a-zA-Z]+)  )

    Connection connection = null;

    */


