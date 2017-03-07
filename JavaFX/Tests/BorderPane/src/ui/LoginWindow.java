package ui;

import core.CoreSettings;
import database.UserDataAccess;
import database.UserRecord;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import authentication.Digest;

/**
 * Created by takayukihoshino on 3/4/17.
 */
public class LoginWindow extends PopupWindow {

    private CoreSettings coreSettings;
    private PromptBox userNameBox;
    private PasswordBox passwordBox;
    private UserProfile userProfile;

    public LoginWindow( CoreSettings coreSettings, String windowTitle ) {

        super( windowTitle, 300, 300 );
        this.coreSettings = coreSettings;
        userNameBox = addPromptBox( "User name" );
        passwordBox = addPasswordBox( "Password" );
        Button loginButton = addButton( "Login" );
        loginButton.setOnAction( click -> {
            UserDataAccess userDB = coreSettings.userDBAccess;
            System.err.println( getUserProfile().toString() );
        } );
//        super.showAndWait();
    }

    public String readUserName() {
        return userNameBox.getInput();
    }

    public Digest getDigest() {

        System.err.println( "getDigest(): password input = " + passwordBox.getInput() );
        return new Digest( userNameBox.getInput(), passwordBox.getInput() );
    }

    public UserProfile getUserProfile() {

        String username = userNameBox.getInput();

        UserDataAccess userDB = coreSettings.userDBAccess;
        return new UserProfile(
                userDB.getUserDataForUsernameAndDigest( username, getDigest() ) );
    }

}
