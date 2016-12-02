package UI;

import javafx.scene.layout.FlowPane;

/**
 * Created by takayukihoshino on 11/16/16.
 */
public class RecipeHeaderListPane extends FlowPane {

    private double height = 700;
    private final double width = 500;
    private final double vGap = 10;
    private final double hGap = 8;
    private final double preferedWidth = 300;
    private String cssSpec;

    public RecipeHeaderListPane() {
        super();
        setVgap( vGap );
        setHgap( hGap );
        setPrefWidth( preferedWidth );
        setPrefHeight( height );
        setPrefWrapLength( preferedWidth -50 );
        setStyle( cssSpec );
    }

    public RecipeHeaderListPane( int vGap, int hGap,
                               int preferredWidth ) {
        super();
        setVgap( vGap );
        setHgap( hGap );
        setPrefWidth( preferedWidth );
        setPrefHeight( height );
        setPrefWrapLength( preferedWidth -50 );
        setPrefWrapLength( preferredWidth );
    }

    public void setCSSSpec( String cssSpec ) {
        this.cssSpec = cssSpec;
        setStyle( cssSpec );
    }


}
