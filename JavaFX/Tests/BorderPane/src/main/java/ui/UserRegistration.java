package ui;

import java.io.IOException;
import java.net.URL;

import authentication.Digest;
import core.user_authorization.UserDetailInput;
import core.user_authorization.UserDetailInputWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import core.Main;
import core.user_authorization.UserRegistrationWindowController;
import myutils.ExceptionHandler;
import myutils.FXMLUtils;

import javax.xml.stream.Location;

/**
 * Created by takayukihoshino on 4/3/17.
 */
public class UserRegistration {

    private boolean newUserIsRegistered;
    UserRegistrationWindowController registrationWindow;
    UserDetailInput detailInput;


    public UserRegistration( Stage stage ) {

        Parent root = null;
        UserRegistrationWindowController registrationWindow = null;
        
        try {
            FXMLLoader loader = new FXMLLoader();
            Class runtimeClass = UserRegistrationWindowController.class;
            URL url = runtimeClass.getResource("/fxml/UserRegistrationWindowController.fxml" );
            loader.setLocation( url );
            root = loader.load();
            registrationWindow = loader.getController();
        } catch ( IOException e ) {
            new ExceptionHandler().handleCriticalException( e );
        }

        registrationWindow.getJoinButton().setOnAction( action -> {
            try {
                handleButtonAction( action );
            } catch( IOException e) {
                new ExceptionHandler().handleNonCriticalException( e );
            }
        } );

        registrationWindow.getCancelButton().setOnAction( action -> {
            try {
                handleButtonAction( action );
            } catch( IOException e ) {
                new ExceptionHandler().handleNonCriticalException( e );
            }
        } );

        stage.setScene( new Scene( root ) );
        stage.show();
    }

    public boolean registrationIsSuccessful() {

        return newUserIsRegistered;
    }

    /***
     * <strong>handleEitherButtonAction()</strong>
     * Handles an action on either button.
     * @param event action event
     * @throws IOException if required FXML file could not be loaded
     */
    private void handleButtonAction( ActionEvent event ) throws IOException {

        Stage stage = null;
        Parent root = null;

        if( event.getSource()== registrationWindow.getJoinButton() ){
            handleJoinButtonAction();
        } else if ( event.getSource() == registrationWindow.getCancelButton() ) {
            handleCancelButtonAction();
        } else {
            assert( false );
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /***
     * <strong>handleJoinButtonAction()</strong>
     * Determines whether a requested username is still available, and if so,
     * collects the new user's profile information and stores it in the user database.
     * Assumes that the given username is already validated for its length and
     * the types of included characters.
     * @return true if new user registration was successful.
     */
    private boolean handleJoinButtonAction() throws IOException {

        String requestedUserName = registrationWindow.getUserNameTextField().getText();
        if ( Main.coreSettings.userDBAccess.usernameIsTaken( requestedUserName ) ) {
            newUserIsRegistered = false;
            notifyUnavailableUserName( requestedUserName );
        } else { // the requested username is available, and a password has been specified
            String password1 = registrationWindow.getPasswordField1().getText();
            String password2 = registrationWindow.getPasswordField2().getText();
            assert( password1.equals( password2 ) );
            detailInput = new UserDetailInput( requestedUserName, password1 );
            if ( detailInput.userDetailInputIsSuccessful ) {
                newUserIsRegistered =
                        storeUserDetails( requestedUserName, password1,
                                detailInput.getEmailAddress(), detailInput.getMobileNumber() );
            } else {
                newUserIsRegistered = false;
            }
        }
        return newUserIsRegistered;
    }

    private boolean storeUserDetails( String userName, String password, String emailAddress, String mobileNumber) {

        int userID = Main.coreSettings.userDBAccess.addNewUser( userName, emailAddress, mobileNumber, new Digest(password) );
        return true;
    }

    private void notifyUnavailableUserName( String requestedUserName ) {

        String message =
                String.format( "Sorry: Username \"%s\" is not available. Please choose another one.",
                        requestedUserName );
        registrationWindow.updatePasswordMsgTextArea( message );
    }

    private boolean userNameIsAvaiable( String requestedUserName ) {

        return false;
    }


    private void handleCancelButtonAction() {

        assert( false );
    }

}
