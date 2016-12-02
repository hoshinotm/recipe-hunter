package UI;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by takayukihoshino on 11/19/16.
 */

public class Browser extends Region {


    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();
    private String currentURL;

    public Browser() {
        super();
        getStyleClass().add( "browser" );
        getChildren().add( browser );
    }

    public Browser( String initialPage ) {
        super();
        getStyleClass().add( "browser" );
        webEngine.load( this.currentURL = initialPage );
        getChildren().add( browser );
    }

    public void load( String page ) {
        webEngine.load( page );
    }

    @Override
    protected void layoutChildren() {
        double width = getWidth();
        double height = getHeight();
        layoutInArea(browser,0,0, width, height,0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 500;
    }
}