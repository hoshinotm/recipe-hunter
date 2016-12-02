package UI;

import core.RecipeHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import myutils.ReportingUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;

/**
 * Created by takayukihoshino on 11/14/16.
 */
public class RecipeHeaderListBox {

    static final int TITLE_COLUMN_WIDTH = 350;
    static final int THUMBNAIL_COLUMN_WIDTH = 50;
    static final int TABLE_ROW_HEIGHT = 15;
    static final int NUMBER_OF_ROWS = 30;
    static final int HORIZONTAL_PADDING = 25 * 2;
    static final int VERTICAL_PADDING = 12 * 2;
    static final int HEADER_HEIGHT = 70;
    static final int TABLE_WIDTH = TITLE_COLUMN_WIDTH + THUMBNAIL_COLUMN_WIDTH + HORIZONTAL_PADDING;
    static final int TABLE_HEIGHT = TABLE_ROW_HEIGHT * NUMBER_OF_ROWS + HEADER_HEIGHT + VERTICAL_PADDING;

    private Stage stage;
    private ArrayList<RecipeHeader> recipeHeaders;
    private String keyword;
    private TableView<RecipeHeaderForDisplay> table;
    private TableColumn recipeTitleColumn;
    private TableColumn thumbnailColumn;
    private RecipeHeaderListScene recipeHeaderListScene;
    private RecipeDisplayScene recipeDisplayScene;

    public RecipeHeaderListBox( Stage stage ) {
        this.stage = stage;
        configureBox();
    }

    public RecipeHeaderListBox( Stage stage,
                                ArrayList<RecipeHeader> recipeHeaders,
                                String keyword,
                                RecipeHeaderListScene recipeHeaderListScene,
                                RecipeDisplayScene recipeDisplayScene ) {
        this.stage = stage;
        this.keyword = keyword;
        String title = "Top-Ranked Recipes with \"" + this.keyword + "\"";
        this.stage.setTitle( title );
        this.recipeHeaders = recipeHeaders;
        this.recipeHeaderListScene = recipeHeaderListScene;
        this.recipeDisplayScene = recipeDisplayScene;

        configureBox();
    }

    private void configureBox() {

        stage.setTitle( "Search Results" );
        stage.setMinWidth( TABLE_WIDTH );
        stage.setMinHeight( TABLE_HEIGHT );

        this.table = new TableView<>();

        this.thumbnailColumn = new TableColumn( "Image" );
        thumbnailColumn.setMinWidth( THUMBNAIL_COLUMN_WIDTH );
        thumbnailColumn.setMaxWidth( THUMBNAIL_COLUMN_WIDTH );
        thumbnailColumn.setCellValueFactory(
                new PropertyValueFactory<RecipeHeaderForDisplay, String>( "imageLink" ) );

        this.recipeTitleColumn = new TableColumn( "Recipe Title" );
        recipeTitleColumn.setMinWidth( TITLE_COLUMN_WIDTH );
        recipeTitleColumn.setMaxWidth( TITLE_COLUMN_WIDTH );
        recipeTitleColumn.setCellValueFactory(
                new PropertyValueFactory<RecipeHeaderForDisplay, String>( "recipeLink" ) );

    }


    public void show() {

        // Load the list with recipeHeaders
        final ObservableList<RecipeHeaderForDisplay> data =
                FXCollections.observableArrayList();
        // TODO Is this taking too long?

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ReportingUtils.testingInfo("Header table building started at " + stopWatch.getTime() + " sec..." );

        this.recipeHeaders.parallelStream().map(h -> data.add(getRecipeHeaderForDisplay( h )));

//        this.recipeHeaders.forEach( h -> {
//            data.add( getRecipeHeaderForDisplay( h ) );
//        } );

        this.table.setItems( loadRecipeHeaderListForDisplay() );
        this.table.getColumns().addAll( this.thumbnailColumn, this.recipeTitleColumn );

        stopWatch.stop();
        ReportingUtils.testingInfo("Header table building stopped at " + Double.toString(stopWatch.getTime()/1000) + " sec");


        // Assign a title to the box
        final Label label = new Label( "Recipes with \"" + keyword + "\"" );
        label.setFont( new Font( "Calibri", 20 ) );

        // Assign the title and the list to the scene
        final VBox vbox = new VBox();
        vbox.setSpacing( 5 );
        vbox.setPadding( new Insets( 10, 0, 0, 10 ) );
        vbox.getChildren().addAll( label, table );

        assert( this.recipeHeaderListScene.getRoot()
                    instanceof RecipeHeaderListPane );
        RecipeHeaderListPane pane = (RecipeHeaderListPane)this.recipeHeaderListScene.getRoot();
        pane.getChildren().addAll( vbox );

        // Show the list inside the box
        this.stage.setScene( this.recipeHeaderListScene );
        this.stage.show();
    }


    private ObservableList<RecipeHeaderForDisplay> loadRecipeHeaderListForDisplay() {

        final ObservableList<RecipeHeaderForDisplay> data =
                FXCollections.observableArrayList();

        recipeHeaders.forEach( h -> data.add( getRecipeHeaderForDisplay( h ) ) );
        return data;

    }


    private RecipeHeaderForDisplay getRecipeHeaderForDisplay( RecipeHeader recipeHeader ) {
        RecipeHeaderForDisplay headerForDisplay =
                new RecipeHeaderForDisplay(
                        recipeHeader.getTitle(),
                        recipeHeader.getF2f_url().toExternalForm(),
                        recipeHeader.getImage_url().toExternalForm(),
                        this.recipeDisplayScene );
        return headerForDisplay;
    }

}
