package ui;

import database.UserRecord;

import java.util.ArrayList;

/**
 * Created by takayukihoshino on 1/16/17.
 */
public class UserProfile {

    private UserName userName;
    private String email;
    private String phoneNumber;
    private ArrayList<SessionID> sessions;

    public UserProfile( UserName userName, String email, String phoneNumber ) {

        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserProfile( UserRecord userRecord ) {

        this.userName = new UserName( userRecord.getUserName() );
        this.email = userRecord.getEmail();
        this.phoneNumber = userRecord.getPhoneNumber();
    }

    public UserName getUserName() {

        return userName;
    }

    public void setUserName( UserName userName ) {

        this.userName = userName;
    }

    public boolean addSession( SessionID sessionID ) {

        if ( ! sessions.contains( sessionID ) ) {
            sessions.add( sessionID );
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<SessionID> getSessions() {

        return sessions;
    }

    public boolean hadSession( SessionID sessionID ) {

        return ( sessions.contains( sessionID ) );

    }

    public String getEmail() {

        return email;
    }

    public void setEmail( String email ) {

        this.email = email;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {

        this.phoneNumber = phoneNumber;
    }
}
