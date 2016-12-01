package core;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import UI.*;
import myutils.ErrorLog;
import myutils.ReportingUtils;


public class Main extends Application {

    RecipeDataAccess dataAccess;

    protected ErrorLog errorLog;
    protected ReportingUtils debug;

    private final static String IMAGE_FILE_FOLDER =
            "/Develop/Eclipse/DukeRecommender/SimpleHTTPClientExperiment/images/";
    private final static String KEYWORD_INPUT_PROMPT = "Enter a keyword here";


    private static final String DEFAULT_CSS_SPEC =
            "-fx-background-image: url(\"file://" +
            IMAGE_FILE_FOLDER +
//            "carrots.jpg" +
//            "green-pepper.jpg" +
            "red-chilli.jpg" +
            "\"); ";

    // main() is invoked first.
    // The main() method is not required for JavaFX applications
    // when the JAR file is created using JavaFX Packager tool.
    // JavaFX Packager tool would embed the JavaFX Launcher in
    // the JAR file.
    public static void main(String[] args) {
        System.out.println("I'm in main()");
        launch(args);
    }

    // Then init()
    // No scene may be crated in init()
    @Override
    public void init() {
        printParams( this.getParameters() );
        this.errorLog = new ErrorLog( System.err );
        this.debug = new ReportingUtils();
    }

    // Then start() is invoked.
    public void start( Stage primaryStage ) {

        Stage theStage = primaryStage;

        // Define key search UI containers
        SearchKeyInputPane searchKeyInputPane =
                new SearchKeyInputPane();
        SearchKeyInputScene searchKeyInputScene =
                new SearchKeyInputScene( searchKeyInputPane );
        searchKeyInputPane.setStyle( DEFAULT_CSS_SPEC );
        searchKeyInputScene.getStylesheets().add
                (Main.class.getResource("SearchKeyInput.css" ).toExternalForm());

        // Define recipe header display containers
        RecipeHeaderListPane recipeHeaderListPane =
                new RecipeHeaderListPane();
        recipeHeaderListPane.setStyle( DEFAULT_CSS_SPEC );
        RecipeHeaderListScene recipeHeaderListScene =
                new RecipeHeaderListScene( recipeHeaderListPane );
        recipeHeaderListScene.getStylesheets().add
                (Main.class.getResource("RecipeHeaderList.css" ).toExternalForm());
        RecipeDisplayScene recipeDisplayScene =
                new RecipeDisplayScene( theStage, new Browser() );



        // TODO Is "dataAccess" necessary?
        this.dataAccess = new RecipeDatabase( RecipeDataSource.FOOD2FORK );

        // final List<String> paramList = this.getParameters().getRaw();

        // Define text input field for search key
        SearchKeyInputField searchKeyInputField =
                new SearchKeyInputField( searchKeyInputPane,
                        KEYWORD_INPUT_PROMPT ); //TODO Doesn't show
        searchKeyInputField.setEditable( true );
        searchKeyInputField.requestFocus();  // TODO Doesn't work

        // Define buttons for sending keyword input
        KeySearchButton keySearchButton =
                new KeySearchButton( searchKeyInputPane, "Search" );
        keySearchButton.setDefaultButton( true );
        keySearchButton.setOnAction( event ->
                    searchKeyEventHandler( event,
                                            theStage,
                                            searchKeyInputField,
                                            this.dataAccess,
                                            recipeHeaderListScene,
                                            recipeDisplayScene )
        );

        // Define a button for clearing keyword input
        KeyClearButton keyClearButton = new KeyClearButton( searchKeyInputPane, "Clear" );
        keyClearButton.setOnAction(
                event -> clearKeyEventHandler( event,
                                               searchKeyInputField,
                                               this.dataAccess )
        );

        // TODO Generate clickable attribution

        TextFlow attribution = generateAttribution( "Powered By ",
                                                     dataAccess.getSourceName(),
                                                     dataAccess.getSourceWebURL(),
                                                     theStage,
                                                     new Scene( new Pane(), 700, 500) );
        HBox hBox0 = new HBox();
        hBox0.setAlignment( Pos.TOP_RIGHT );
        hBox0.getChildren().add( new Group( attribution ) );
        Label titlePreface = new Label( "Powered By " );
        titlePreface.setFont( UISettings.titleFont );
        hBox0.getChildren().addAll( titlePreface, attribution );

        // 1st horizontal box is just for search key input field for now
        HBox hBox1 = new HBox();
        hBox1.getChildren().add( searchKeyInputField );
        // 2nd one for the keyword search and clear buttons
        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll( keySearchButton, keyClearButton );
        // Arrange the two horizonal boxes vertically
        VBox vBox = new VBox( hBox0, hBox1, hBox2 );
        // Paste the whole thing onto the keyword input pane
        searchKeyInputPane.getChildren().add( vBox );
        // Title the stage, then attach the scene to the stage.
        Label appTitle = new Label("Recipkr");
        appTitle.setFont( UISettings.titleFont );

        theStage.setTitle( appTitle.toString() );
        theStage.setScene( searchKeyInputScene );
        theStage.show();

    }

    private TextFlow generateAttribution( String preface, String linkText, String linkURL, Stage stage, Scene newScene ) {
        Hyperlink sourceLink = new Hyperlink( linkText );
        sourceLink.setOnAction( event -> {
            new Browser().load( "http://"+ linkURL );
            Scene savedScene = stage.getScene();
            stage.setScene( newScene );
            stage.show();
            stage.setScene( savedScene );
        } );
        return new TextFlow( new Text( preface ), sourceLink );
    }

    private void searchKeyEventHandler( ActionEvent event,
                                        Stage theStage,
                                        SearchKeyInputField searchKeyField,
                                        RecipeDataAccess dataAccess,
                                        RecipeHeaderListScene headerListScene,
                                        RecipeDisplayScene recipeDisplayScene ) {

        ReportingUtils.testingInfo( "In searchKeyEventHandler()..." );
        String key = searchKeyField.getText();
        searchKeyField.clear();
        theStage.setTitle( "Searching for recipes with \"" + key + "\"..." );
        dataAccess.clearKeywords();
        dataAccess.addKeyword( key );
        searchAndReportRecipeHeaders( theStage,
                                      dataAccess,
                                      headerListScene,
                                      recipeDisplayScene );
    }


    private void searchAndReportRecipeHeaders( Stage theStage,
                                               RecipeDataAccess dataAccess,
                                               RecipeHeaderListScene recipeHeaderListScene,
                                               RecipeDisplayScene recipeDisplayScene ) {
        ArrayList<RecipeHeader> headerList =
                searchForKey( (RecipeDatabase)dataAccess );
        if ( headerList == null ) {
            this.errorLog.write( "RecipeDataBase.searchForKey() has returned a null list" );
        } else {
            theStage.setTitle( "Recipe" );
            recipeDisplayScene.setInitialSize( 750, 500 );
            recipeDisplayScene.setColor( Color.web("#666970") );
            theStage.setScene( recipeDisplayScene );
            RecipeHeaderListBox headerListBox =
                    new RecipeHeaderListBox( theStage,
                            headerList,
                            dataAccess.getKeywords(),
                            recipeHeaderListScene,
                            recipeDisplayScene );
            headerListBox.show() ;
        }

    }

    private void clearKeyEventHandler( ActionEvent event,
                                       SearchKeyInputField searchKeyField,
                                       RecipeDataAccess dataAccess ) {
        if ( searchKeyField != null ) {
            searchKeyField.clear();
            searchKeyField.requestFocus();
        }
        dataAccess.clearKeywords();

    }

    private String readSearchKeyText( SearchKeyInputField keyField ) {

        String keyword = keyField.getText();
        if ( keyword.equalsIgnoreCase( KEYWORD_INPUT_PROMPT ) ) {
            keyField.markUnedited();
            keyword = "";
        } else {
            keyField.markEdited();
        }
        System.err.println(keyword);
        ReportingUtils.testingInfo( this.getClass().getName() + "(): Key = \"" + keyword + "\"");
        return keyword;
    }

    private ArrayList<RecipeHeader> searchForKey( RecipeDatabase database ) {

        ArrayList<RecipeHeader> headers = database.requestRecipeHeaders( 1 );
        if ( headers == null ) {
            this.errorLog.write( "RecipeDataBase.requestRecipeHeaders() has returned a null list" );
        } else {
            headers.forEach( h -> ReportingUtils.testingInfo( h.toString() ) );
        }

        return headers;

    }

    /***
     * Recipe header list display methods start here.
     */

    /***
     * Utility methods
     */
    private void printParams( Application.Parameters params ) {
        ReportingUtils.testingInfo( "Raw parameters: " );
        params.getRaw().forEach( p -> ReportingUtils.testingInfo( p+" " ) );
        // ReportingUtils.testingInfo("\n");

        ReportingUtils.testingInfo( "Named Parameters: ");
        params.getNamed().forEach( (k, v)->
                ReportingUtils.testingInfo( "<"+k+","+v+"> " ) );

        ReportingUtils.testingInfo( "Unnamed Parameters: ");
        params.getUnnamed().forEach( p -> ReportingUtils.testingInfo( p+" " ) );
    }

}