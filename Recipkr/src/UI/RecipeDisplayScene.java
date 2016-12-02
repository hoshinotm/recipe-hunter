package UI;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by takayukihoshino on 11/21/16.
 */
public class RecipeDisplayScene extends Scene {

    private Stage stage;
    private Browser rootBrowser;
    private double initialHSize, initialVSize;
    private Color color;

    public RecipeDisplayScene( Stage stage,
                               Browser rootBrowser ) {
        super( rootBrowser );
        this.stage = stage;
        this.rootBrowser = rootBrowser;
    }

    public RecipeDisplayScene( Stage stage,
                               Browser rootBrowser,
                               double initialHSize,
                               double initialVSize,
                               Color color ) {
        super( rootBrowser, initialHSize, initialVSize, color );
        this.stage = stage;
        this.rootBrowser = rootBrowser;
        this.initialHSize = initialHSize;
        this.initialVSize = initialVSize;
    }

    public Stage getStage() {
        return this.stage;
    }

    public Browser getRootBrowser() {
        return rootBrowser;
    }

    public void setRootBrowser( Browser rootBrwoser ) {
        this.rootBrowser = rootBrwoser;
    }

    public double getInitialHSize() {
        return this.initialHSize;
    }

    public double getInitialVSize() {
        return this.initialVSize;
    }

    public void setInitialSize( double initialHSize,
                                double initialVSize ) {
        this.initialHSize = initialHSize;
        this.initialVSize = initialVSize;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor( Color color ) {
        this.color = color;
    }

    public void loadPage( String uri ) {
        this.rootBrowser.load( uri );
    }
}
