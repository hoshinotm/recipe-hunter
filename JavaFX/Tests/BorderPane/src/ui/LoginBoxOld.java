package ui;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import core.CoreSettings;


/**
 * Created by takayukihoshino on 12/3/16.
 */
public class LoginBoxOld extends Pane {

    private CoreSettings coreSettings;
    private TextFlow welcome;
    private UserNameBox userNameBox;
    private PasswordBox passwordBox;
    private ButtonBox buttonBox;
    private AlertBox alertBox;

    protected int row;

    public LoginBoxOld( CoreSettings coreSettings ) {
        this.coreSettings = coreSettings;
        this.welcome = new TextFlow( new Text( UISettings.welcomeLead ) );
        this.userNameBox = new UserNameBox( coreSettings );
        this.passwordBox = new PasswordBox( coreSettings );
        this.alertBox = new AlertBox();
        getChildren().addAll( welcome, userNameBox, passwordBox, alertBox );
    }

    public String getUserName() {
        return userNameBox.getUserName();
    }

    protected String getPassword() {
        return passwordBox.getPassword();
    }

    public void setAlertText( String text ) {

        this.alertBox.setText( text );
    }

    public void updateAlertText( String newText ) {

        this.alertBox.setText( newText );
    }

    public String getAlertText() {

        return this.alertBox.getText();
    }

    private class UserNameBox extends HBox {

        private Label userNameLabel;
        private TextField userNameField = new TextField();

        private UserNameBox( CoreSettings coreSettings ) {
            this.userNameLabel = new Label( UISettings.userNameLabel );
            this.userNameField = new TextField( UISettings.userNamePrompt );
            this.userNameField.setEditable( true );  // Actually true is default.
        }

        private String getUserName() {
            return userNameField.getText();
        }
    }

    private class PasswordBox extends HBox {

        private Label passwordLabel;
        private PasswordField passwordField;

        private PasswordBox( CoreSettings coreSettings ) {
            this.passwordLabel = new Label( UISettings.passwordLabel );
            this.passwordField = new PasswordField();
            this.passwordField.setEditable( true );
        }

        private String getPassword() {
            return passwordField.getText();
        }
    }

    private class ButtonBox extends HBox {

        private KeySearchButton keySearchButton;
        private KeyClearButton keyClearButton;

        private ButtonBox() {
        }

        private void setKeySearchButton( KeySearchButton searchButton ) {
            keySearchButton = searchButton;
        }

        private void setKeyClearButton( KeyClearButton clearButton ) {
            keyClearButton = clearButton;
        }
    }

    /***
     * Represents a box for alert/information message.
     *
     */
    private class AlertBox extends HBox {

        private TextField alert;

        private AlertBox() {
            super();
            this.alert = new TextField();
            this.alert.setEditable( false );
        }

        private AlertBox( String initialText ) {
            super();
            this.alert = new TextField( initialText );
            this.alert.setEditable( false );
        }

        private String getText() {
            return alert.getText();
        }

        private void setText( String text ) {
            alert.setText( text );
        }


    }

}




