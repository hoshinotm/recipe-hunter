package UI;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by takayukihoshino on 11/22/16.
 */
public class KeySearchAndRecipeDisplayPane extends BorderPane {

    private Stage stage;
    private Node top, bottom, left, right;

    public KeySearchAndRecipeDisplayPane( Stage stage ) {
        super();
        this.stage = stage;
        setTop( this.top = new HBox() );
        setLeft( this.left = new VBox());
        setRight(this.right = new VBox() );
        setBottom( this.bottom = new HBox() );
    }

    public Stage getStage() {
        return this.stage;
    };

}
