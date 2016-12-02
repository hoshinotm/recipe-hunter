package UI;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

/**
 * Created by takayukihoshino on 11/18/16.
 */
public class ClickableRecipeImage extends Button {

    public ClickableRecipeImage ( Hyperlink hyperlink,
                                  Browser browser,
                                  double prefWidth,
                                  double prefHeight ) {
        super( hyperlink.getText() );
        setMinSize( 20, 20 );
        setPrefSize( prefWidth, prefHeight );
        setOnAction( event -> {
            browser.load ( hyperlink.getText() );
            // Create a event handler / delegates that will open a hyperlink
            // Created from the url
        });

    }
}
