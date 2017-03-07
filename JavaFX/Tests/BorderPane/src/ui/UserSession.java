package ui;

import java.time.OffsetDateTime;

/**
 * Created by takayukihoshino on 1/17/17.
 */
public class UserSession {

    private UserProfile userProfile;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

    public UserSession( UserProfile userProfile ) {

        this.userProfile = userProfile;
        initializeTimeStamps();
    }

    public boolean start() {
        if ( hasStarted() ) {
            return false;
        } else {
            startTime = OffsetDateTime.now();
            return true;
        }
    }

    public boolean end() {
        if ( hasEnded() ) {
            return false;
        } else {
            endTime = OffsetDateTime.now();
            return true;
        }
    }

    public UserProfile getUserProfile() {
        return this.userProfile;
    }

    private void initializeTimeStamps() {
        this.startTime = OffsetDateTime.MIN;
        this.endTime = OffsetDateTime.MIN;
    }

    private boolean hasStarted() {
        return ( this.startTime != OffsetDateTime.MIN );
    }

    private boolean hasEnded() {
        return ( this.endTime != OffsetDateTime.MIN );
    }

}
