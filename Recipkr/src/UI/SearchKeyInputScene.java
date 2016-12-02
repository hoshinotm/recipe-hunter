package UI;

import javafx.scene.Scene;

/**
 * Created by takayukihoshino on 11/16/16.
 */
public class SearchKeyInputScene extends Scene {

    private SearchKeyInputPane pane;
    private double initialWidth;
    private double initialHeight;
    private String cssSpec;
    private SearchKeyInputField keyInputField;
    private KeySearchButton keySearchButton;
    private KeyClearButton keyClearButton;

    public SearchKeyInputScene( SearchKeyInputPane pane ) {
        super( pane );
        this.pane = pane;
        this.initialWidth = pane.getWidth();
        this.initialHeight = pane.getHeight();
        this.cssSpec = pane.getStyle();
    }

    public SearchKeyInputScene( SearchKeyInputPane pane,
                                SearchKeyInputField keyInputField,
                                KeySearchButton keySearchButton,
                                KeyClearButton keyClearButton,
                                double initialWidth,
                                double initialHeight,
                                String cssSpec ) {
        super( pane );
        this.pane = pane;
        this.keyInputField = keyInputField;
        this.keySearchButton = keySearchButton;
        this.keyClearButton = keyClearButton;
        this.initialWidth = initialWidth;
        this.initialHeight = initialHeight;
        this.cssSpec = cssSpec;
        setRoot( pane );
    }

    public SearchKeyInputField getKeyInputField() {

        return keyInputField;
    }

    public void setKeyInputField( SearchKeyInputField keyInputField ) {

        this.keyInputField = keyInputField;
    }

    public KeySearchButton getKeySearchButton() {

        return this.keySearchButton;
    }

    public void setKeySearchButton( KeySearchButton keySearchButton ) {

        this.keySearchButton = keySearchButton;
        this.pane.getChildren().add( keySearchButton );
    }

    public KeyClearButton getKeyClearButton() {

        return this.keyClearButton;
    }

    public void setKeyClearButton( KeyClearButton keyClearButton ) {

        this.keyClearButton = keyClearButton;
        this.pane.getChildren().add( keyClearButton );
    }

    public double getInitialWidth() {

        return this.initialWidth;
    }

    public void setInitialWidth( int initialWidth ) {

        this.initialWidth = initialWidth;

    }

    public double getInitialHeight() {


        return this.initialHeight;
    }

    public void setInitialHeight( int initialHeight ) {

        this.initialHeight = initialHeight;
    }

    public String getCssSpec() {

        return this.cssSpec;
    }

    public void setCssSpec( String cssSpec ) {

        this.cssSpec = cssSpec;
    }

}
