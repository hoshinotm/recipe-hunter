package UI;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/***
 * class RecipeHeaderForDisplay
 *
 * Represents a recipe header in a header list display
 *
 * Created by takayukihoshino on 11/10/16.
 */
public class RecipeHeaderForDisplay {

    private SimpleObjectProperty recipeImage;
    private ImageButton  imageLink;
    private SimpleStringProperty recipeTitle;
    private Hyperlink  recipeLink;
    private RecipeDisplayScene recipeDisplayScene;

    public RecipeHeaderForDisplay( String recipeTitle,
                                   String recipeLinkText,
                                   String imageLinkText,
                                   RecipeDisplayScene recipeDisplayScene ) {
        try {
            ImageView thumbnailImage = new ImageView( imageLinkText ); // TODO
            this.recipeImage = new SimpleObjectProperty( new ImageView( new Image( imageLinkText ) ) );  // TODO
            this.imageLink = new ImageButton( recipeLinkText );
            this.imageLink.setOnAction( event -> {
                recipeDisplayScene.getRootBrowser().load( recipeLinkText );
                recipeDisplayScene.getStage().setScene( recipeDisplayScene );
                recipeDisplayScene.getStage().show();
            } );
            this.recipeTitle = new SimpleStringProperty( recipeTitle );
            this.recipeLink = new Hyperlink( recipeLinkText );
            this.recipeLink.setText( recipeTitle );
            this.recipeLink.setOnAction( event -> {
                recipeDisplayScene.getRootBrowser().load( recipeLinkText );
                recipeDisplayScene.getStage().setScene( recipeDisplayScene );
                recipeDisplayScene.getStage().show();
                }
            );
            this.recipeDisplayScene = recipeDisplayScene;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public String getRecipeTitle() {
        return recipeTitle.get();
    }

    public SimpleStringProperty recipeTitleProperty() {
        return recipeTitle;
    }

    public void setRecipeTitle( String recipeTitle ) {
        this.recipeTitle.set( recipeTitle );
    }

    public Hyperlink getRecipeLink() {
        return recipeLink;
    }

}

