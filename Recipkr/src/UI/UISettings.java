package UI;

import core.Main;
import core.RecipeDataSource;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by takayukihoshino on 11/30/16.
 */
public class UISettings {

    private Stage stage;

    public static final Font titleFont =
            Font.loadFont( Main.class.getResource("MrsSaintDelafield-Regular.ttf").toExternalForm(), 20);
    public static final Font LargeFont =
            Font.loadFont(Main.class.getResource("MrsSaintDelafield-Regular.ttf").toExternalForm(), 15);
    public static final Font smallFont =
            Font.loadFont(Main.class.getResource("MrsSaintDelafield-Regular.ttf").toExternalForm(), 10);
    public static final Font buttonFont =
            Font.loadFont( Main.class.getResource( "MrsSaintDelafield-Regular.ttf" ).toExternalForm(), 15 );
    public static final String searchKeyLabel = "Search";
    public static final String clearKeyLabel = "Clear";
    public static final String keyInputFieldTooltip =
            "Enter recipe keyword here or none for most popular recipes";
    public static final String searchKeyButtonTooltip =
            "Click to start search";
    public static final String clearKeyButtonTooltip =
            "Click to clear keyword";

    public static final String searchKeyInputCSSFile =
            "SearchKeyInput.css";
    public static final String clearKeyInputCSSFile =
            "SearchKeyInput.css";

    public UISettings ( Stage stage ) {
        this.stage = stage;
    }

    public Stage getStage() {

        return stage;
    }

    public void setStage( Stage stage ) {

        this.stage = stage;
    }
}
