package UI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Created by takayukihoshino on 11/15/16.
 */
public class KeySearchButton extends Button {

    private Pane pane;
    private EventHandler eventHandler;
    private String tooltipText = UISettings.searchKeyButtonTooltip;
    private Font font = UISettings.buttonFont;

    public KeySearchButton( Pane pane, String label ) {
        super( label );
        setUp( pane, 70, this.tooltipText, this.font );
    }

    public KeySearchButton( Pane pane, String label,
                            EventHandler eventHandler ) {
        super( label );
        this.eventHandler = eventHandler;
        setUp( pane, 70, this.tooltipText, this.font );
    }

    private void setUp( Pane pane,
                        double prefWidth,
                        String tooltipText,
                        Font font ) {
        this.pane = pane;
        setPrefWidth( prefWidth );
        this.getStyleClass().add("button");
        setTooltip( new Tooltip( tooltipText ) );
        setFont( font );
    }

    public Pane getPane() {

        return pane;
    }

    public EventHandler getEventHandler() {

        return eventHandler;
    }

    public void setEventHandler( EventHandler eventHandler ) {

        this.eventHandler = eventHandler;
    }

    public String getTooltipText() {

        return tooltipText;
    }

    public void setTooltipText( String tooltipText ) {
        this.tooltipText = tooltipText;
    }

}
