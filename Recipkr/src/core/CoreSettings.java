package core;

import UI.UISettings;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

/**
 * Created by takayukihoshino on 12/2/16.
 */
public class CoreSettings {

    public static final String appName = "Recipikr";

    public static final String attributionPreface =
            "Powered By ";
    public static final String dataSourceName =
            RecipeDataSource.FOOD2FORK.name;
    public static final String dataSourceWebURL =
            RecipeDataSource.FOOD2FORK.webURL;

    private UISettings uiSettings;

    public CoreSettings ( Stage stage ) {
        this.uiSettings = new UISettings( stage );
    }

    public Stage getStage() {
        return this.uiSettings.getStage();
    }

}
