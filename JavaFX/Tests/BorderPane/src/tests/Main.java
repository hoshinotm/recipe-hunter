package tests;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import twilio.MyTwilio;
import ui.PopupWindow;

/**
 * Created by takayukihoshino on 2/23/17.
 */
public class Main extends Application {

    public static void main( String[] args ) {
        launch( args );
    }

    public void start( Stage primaryStage )  {

        PopupWindow webPageWindow =
                new PopupWindow( "Web page window example",800, 500 );
        webPageWindow.addWebPage( "http://cnn.com" );
        webPageWindow.addCloseButton();
        webPageWindow.showAndWait();

        PopupWindow popupWindow =
                new PopupWindow( "Text a recipe link?", 150, 150 );
        popupWindow.addText( "Text a link for this recipe?" );
        Button yesTextButton = popupWindow.addButton( "Yes, text recipe link" );
        yesTextButton.setOnAction( event -> {
                    textMsg("7734252824", "Hello!" );
        });
        Button noDontTextButton =
                popupWindow.addButton( "No, don't text recipe link" );
        noDontTextButton.setOnAction( event -> { /* Do nothing */ } );
        Button closeButton = popupWindow.addCloseButton();
        closeButton.setDefaultButton( true );
        popupWindow.showAndWait();

    }

    private void textMsg( String number, String msg ) {
        new MyTwilio( number, msg ).send();
    }

}
