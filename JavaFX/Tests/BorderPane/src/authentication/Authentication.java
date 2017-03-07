package authentication;

import com.sun.org.apache.bcel.internal.classfile.Code;
import core.CoreSettings;
import database.UserDataAccess;
import myutils.Utils;
import ui.LoginWindow;
import ui.PopupWindow;
import ui.UserName;
import ui.UserProfile;
import javafx.stage.Stage;

/**
 * Created by takayukihoshino on 1/23/17.
 */
public class Authentication {

    private Stage stage;
    private boolean twoFactors;
    private Utils debug;
    private UserProfile userProfile;

    public Authentication( Stage stage, boolean authenticateWithTwoFactors ) {

        this.stage = stage;
        this.twoFactors = authenticateWithTwoFactors;
    }

    public UserProfile login( CoreSettings coreSettings, String windowTitle ) {

        LoginWindow loginWindow = new LoginWindow( coreSettings, "Login" );
        loginWindow.showAndWait();
        userProfile = loginWindow.getUserProfile();
        if ( userProfile != null  && twoFactors ) {
            CodeValidationWindow validationWindow =
                    new CodeValidationWindow( coreSettings, windowTitle );
            validationWindow.showAndWait();
        }
        return ( userProfile = loginWindow.getUserProfile() );
    }

    // TODO Implement
    public UserProfile getUserProfile() {

        UserProfile userProfile;


        return new UserProfile( new UserName( "default-user" ),
                "hoshinot@gmail.com",
                "+17734252824" );
        // UserDataAccess db = new UserDataAccess( new CoreSettings( stage).)
    }

}
