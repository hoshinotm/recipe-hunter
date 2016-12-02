package UI;

import core.RecipeDataSource;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextFlow;

/**
 * Created by takayukihoshino on 11/16/16.
 */
public class SearchKeyInputPane extends FlowPane {

    private final double vGap = 20;
    private final double hGap = 10;
    private final double preferredHeight = 100;
    private final double preferredWidth = 350;
    private final double preferredWrapLength =
                    preferredWidth - 60;
    private String cssSpec;

    public SearchKeyInputPane() {
        super();
        setVgap( vGap );
        setHgap( hGap );
        setPrefHeight( preferredHeight );
        setPrefWidth( preferredWidth );
        setPrefWrapLength( preferredWrapLength );
        getStyleClass().add("pane");
        // setStyle( cssSpec );
    }
    
    public SearchKeyInputPane( double vGap, double hGap,
                               double preferredWidth ) {
        super();
        setVgap( vGap );
        setHgap( hGap );
        setPrefWidth( this.preferredWidth );
        setPrefHeight( this.preferredHeight );
        setPrefWrapLength( this.preferredWrapLength );
        getStyleClass().add("pane");
        // setStyle( cssSpec );
    }
//
//    public void addAttribution( ) {
//        String linkText = RecipeDataSource.F
//        Hyperlink sourceLink = new Hyperlink( linkText );
//        sourceLink.setOnAction( event -> {
//            new Browser().load( "http://"+ linkURL );
//            Scene savedScene = stage.getScene();
//            stage.setScene( newScene );
//            stage.show();
//            stage.setScene( savedScene );    }

    public void setCSSSpec( String cssSpec ) {
        this.cssSpec = cssSpec;
        setStyle( cssSpec );
    }


}
