/**
 * Created by takayukihoshino on 2/23/17.
 */

package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class PopupWindow extends FlowPane {
    
    private static final int PANE_HGAP = 5;
    private static final int PANE_VGAP = 5;
    private static final int PANE_WIDTH = 300;
    private static final int PANE_HEIGHT = 200;
    private static final int WEB_PANE_WIDTH = 700;
    private static final int WEB_PANE_HEIGHT = 500;
    private static final int MIN_WIDTH = 300;

    private FlowPane textPane;
    private FlowPane webPagePane;
    private ArrayList<Button> buttons;
    private ArrayList<FlowPane> panes;
    private VBox vBox;
    private FlowPane buttonPane;
    private Scene newScene;
    private Stage newStage;
    private String windowTitle;
    private int width;
    private int height;

    public PopupWindow( String title, int width, int height ) {

        setupPopupWindow( title );
        this.width = width;
        this.height = height;
    }

    public PopupWindow( String title ) {

        setupPopupWindow( title );
    }

    public void setWidth( int width ) {

        this.width = width;
    }

    public void setHeight( int height ) {

        this.height = height;
    }

    public void setupPopupWindow( String title ) {

        this.buttons = new ArrayList<>();
        this.panes = new ArrayList<>();

        // Assign an empty VBox to a new popup window scene.
        // To populate the VBox with contents, invoke the addXXX methods defined below.
        vBox = new VBox();
        vBox.setAlignment( Pos.CENTER );
        newScene = new Scene( vBox, width, height );
        newScene.getStylesheets().add( "SearchKeyInput.css" );
        // Create a new popup stage for this scene
        newStage = new Stage();
        newStage.setScene( newScene );
        newStage.initModality( Modality.APPLICATION_MODAL );
        newStage.setTitle( windowTitle );
    }

    /***
     *   addWebPage()
     *   Add to this window a pane with the content from the given web page.
     *   Add the new pane to 'panes'.
     *   @param url web page url string
     *   @return FlowPane object
     */
    public FlowPane addWebPage( String url ) {

        FlowPane webPagePane =
                setUpWebPagePane( url, width-10, height -150 );
        panes.add( webPagePane );
        vBox.getChildren().add( webPagePane );
        return webPagePane;
    }

    /***
     *  addPromptBox()
     *  Add to this window a pane with a prompt text and a text field.
     *
     */
    public PromptBox addPromptBox( String promptText ) {

        PromptBox promptBox = new PromptBox( promptText );
        vBox.getChildren().add( promptBox );

        return promptBox;
    }

    /***
     *  addPasswordBox()
     *  Add to this window a pane with a prompt text and a text field.
     *  Mask each entered character with a filled circle.
     */
    public PasswordBox addPasswordBox( String promptText ) {

        PasswordBox passwordBox = new PasswordBox( promptText );
        vBox.getChildren().add( passwordBox );
        return passwordBox;
    }

    /***
     *   addButton()
     *   Add to this window's button pane a new button with the given label
     *   Add the button pane to the window if it this is a first button.
     *   A event handler for the new button needs to be
     *   defined by the invoking method.
     *   @param label label text string
     *   @return Button object
     */
    public Button addButton( String label ) {

        if ( buttons.size() == 0 ) {
            buttonPane = setUpButtonPane();
            panes.add( buttonPane );
            vBox.getChildren().add( buttonPane );
        }
        Button newButton = createANewButton( label );
        buttonPane.getChildren().add( newButton );
        return newButton;
    }

    /***
     *   addCloseButton()
     *   Add to this window a new pane with a button with given label.
     *   Define a event handler that simply closes this window.
     *   Add the new button to 'buttons' and its pane to
     *   buttonPanes"
     *   @param label button label string
     *   @return Button object
     */
    public Button addCloseButton( String label ) {

        if ( buttons.size() == 0 ) {
            buttonPane = setUpButtonPane();
            panes.add( buttonPane );
            vBox.getChildren().add( buttonPane );
        }
        Button closeButton = createANewButton( label );
        closeButton.setOnAction( event ->
                this.close()
        );
        buttonPane.getChildren().add( closeButton );
        return closeButton;
    }

    /***
     *  addCloseButton()
     *  Same as the above except the new button will have "Close" label
     */
    public Button addCloseButton() {

        return addCloseButton( "Close" );
    }

    /***
     * addText()
     * Add to this window a new pane with the given text.
     * @param text text string to add
     * @return Pane object
     */
    public FlowPane addText( String text ) {

        FlowPane pane = setUpTextPane( text );
        pane.setAlignment( Pos.CENTER );
        pane.setPrefWrapLength( width - 50 );
        panes.add( pane );
        return pane;
    }

    /***
     * clearContent()
     * Erase all current content of this window.
     */
    public void clearContent() {

        vBox.getChildren().clear();
    }

    private FlowPane setUpTextPane( String text ) {

        Text textField = new Text( text );
        textField.setTextAlignment( TextAlignment.CENTER );
        textField.setWrappingWidth( width - 50 );
        textPane = new FlowPane();
        textPane.setPrefWidth( width-50 );
        textPane.setAlignment( Pos.CENTER );
        textPane.getChildren().add( textField );
        return textPane;
    }

    private FlowPane setUpWebPagePane( String url, int width, int height ) {

        Browser browser = new Browser( url );
        browser.load( url );
        webPagePane = new FlowPane();
        webPagePane.setMinSize( width-50, height );
        webPagePane.setAlignment( Pos.CENTER );
        webPagePane.getChildren().add( browser );
        return webPagePane;
    }

    private FlowPane setUpButtonPane() {

        FlowPane buttonPane = new FlowPane();
        buttonPane.setHgap( PANE_HGAP );
        buttonPane.setVgap( PANE_VGAP );
        buttonPane.setAlignment( Pos.CENTER );
        // TODO Define style in CSS file?
        buttonPane.setStyle( "fx-background-color:tan;-fx-padding:10px;" );
        return buttonPane;
    }

    private Button createANewButton( String buttonLabel ) {

        Button button = new Button( buttonLabel );
        button.setStyle( "fx-background-color:tan;-fx-padding:10px;" );
        buttons.add( button );
        return button;
    }

    public void show() {

        newStage.show();
    }

    public void showAndWait() {

        newStage.showAndWait();
    }

    public void close() {

        newStage.close();
    }
    
    public Object[] getButtons() {
        return buttons.toArray();
    }

}
