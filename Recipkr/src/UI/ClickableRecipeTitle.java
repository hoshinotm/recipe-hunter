package UI;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

/**
 * Created by takayukihoshino on 11/17/16.
 */
public class ClickableRecipeTitle extends Button {

    public ClickableRecipeTitle ( Hyperlink hyperlink, Browser browser, double prefWidth ) {
        super( hyperlink.getText() );
        setMinHeight( 20 );
        setPrefWidth( prefWidth );
        setOnAction( event -> {
            browser.load( hyperlink.toString() );
            // Create a event handler / delegates that will open a hyperlink
            // Created from the url
        });

    }

}
