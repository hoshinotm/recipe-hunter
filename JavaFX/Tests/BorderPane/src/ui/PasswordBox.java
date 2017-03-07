package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

/**
 * Created by takayukihoshino on 3/6/17.
 */
public class PasswordBox extends FlowPane {

    private TextField inputField;
    private HBox hBox = new HBox();
    private static final int HBOX_SPACING = 5;

    public PasswordBox( String promptText  ) {

        super();
        hBox.setAlignment( Pos.CENTER );
        hBox.setSpacing( HBOX_SPACING );
        setAlignment( Pos.CENTER );
        Label label = new Label( promptText );
        hBox.getChildren().add( label );
        inputField = new PasswordField();
        inputField.setPromptText( promptText );
        hBox.getChildren().add( inputField );
        getChildren().add( hBox );
        setVgap( 5 );
    }

    public String getInput() {

        return this.inputField.getText();
    }

    public void clearInput() {

        this.inputField.clear();
    }

}
