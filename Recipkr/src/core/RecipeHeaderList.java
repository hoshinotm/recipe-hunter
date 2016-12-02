package core;

import UI.RecipeHeaderForDisplay;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Created by takayukihoshino on 11/10/16.
 */
public class RecipeHeaderList {

    private Stage stage;
    private TableView<RecipeHeaderForDisplay> table =
            new TableView<RecipeHeaderForDisplay>();
//    private final ObservableList<RecipeHeaderForDisplay> data =
//            FXCollections.observableArrayList(
//                    new RecipeHeaderForDisplay("How to Make Peepshi = Peeps Sushi", "link", "http://xyz.com/abc"),
//                    new RecipeHeaderForDisplay("Sushi rice bowl with beef, egg &amp; chili sauce", "link", "http://xyz.com/def")
//            );

    public RecipeHeaderList( Stage stage ) {
        this.stage = stage;
    }

//    public Scene define() {
//
//    Scene scene = new Scene(new Group());
//    stage.setTitle( "Table View Sample" );
//    stage.setWidth( 400 );
//    stage.setHeight( 500 );
//
//    final Label label = new Label("Recipes");
//    label.setFont(new RecipeHeaderList( "Arial", 20));
//
//    table.setEditable(true);
//
//    TableColumn recipeTitleColumn = new TableColumn( "Recipe Name" );
//    recipeTitleColumn.setPrefWidth( 360) ;
//    recipeTitleColumn.setCellValueFactory(
//            new PropertyValueFactory<RecipeHeaderForDisplay, String>( "recipeTitle" ) );
//
//    table.setItems(data);
//    table.getColumns().
//
//
//    RecipeHeaderList( recipeTitleColumn );
//
//    final VBox vbox = new VBox();
//    vbox.setSpacing(5);
//    vbox.setPadding(new RecipeHeaderList( 10, 0, 0, 10));
//    vbox.getChildren().
//
//
//    RecipeHeaderList( label, table );
//
//    ((Group) scene.getRoot()).
//
//
//    RecipeHeaderList().
//
//
//    RecipeHeaderList( vbox );
//
//    stage.setScene(scene);
//    stage.show();
//
//}

}
