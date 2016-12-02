package UI;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * Created by takayukihoshino on 11/17/16.
 */
public class SearchKeyInputField extends TextField {

    private boolean hasBeenEdited;

    public SearchKeyInputField( SearchKeyInputPane pane,
                                String prompt ) {
        super();
        setPromptText( prompt );
        setPrefWidth( pane.getPrefWidth() - 60 );
        this.hasBeenEdited = false;
        setTooltip( new Tooltip( UISettings.searchKeyButtonTooltip ) );
    }

    public boolean hasBeenEdited() {
        return this.hasBeenEdited;
    }

    public void markEdited() {
        this.hasBeenEdited = true;
    }

    public void markUnedited() {
        this.hasBeenEdited = false;
    }

}

