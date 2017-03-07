package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

/**
 * Created by takayukihoshino on 3/4/17.
 */
public class PromptBox extends FlowPane {

    private TextField inputField;
    private HBox hBox = new HBox();
    private static final int HBOX_SPACING = 5;
    private static final int VGAP = 5;

    public PromptBox( String promptText  ) {

        super();
        getStyleClass().add( "promptbox" );
        setVgap( VGAP );
        hBox.setAlignment( Pos.CENTER );
        setAlignment( Pos.CENTER );
        hBox.setSpacing( HBOX_SPACING );
        Label label = new Label( promptText );
        hBox.getChildren().add( label );
        inputField = new TextField();
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
