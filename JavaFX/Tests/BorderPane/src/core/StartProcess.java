package core;

import ui.Browser;
import javafx.application.Application;
import javafx.stage.Stage;
import myutils.Utils;

import java.util.concurrent.CountDownLatch;

/**
 * Created by takayukihoshino on 1/19/17.
 */

public class StartProcess extends Application implements Runnable {
    
    public static final CountDownLatch latch = new CountDownLatch( 1 );
    public static StartProcess startProcess = null;
    private Stage stage;
    private String url;

    public StartProcess() {
        assert( false );
    }

    public StartProcess( Stage stage, String url ) {
        Utils.alert( "In StartProcess( Stage, String) " );
        this.stage = stage;
        this.url = url;
        setStartProcess( this );
    }

    public static StartProcess waitForStartProcess() {
        try {
            latch.await();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        return startProcess;
    }


    public static void setStartProcess( StartProcess process ) {
        startProcess = process;
        latch.countDown();
    }

    public void printSomething() {
        System.out.println("You called a method on the application");
    }

    public void start(Stage stage) throws Exception {

//        this.browser.load( this.url );
//        BorderPane pane = new BorderPane();
//        Scene scene = new Scene(pane, 500, 500);
//        this.stage.setScene(scene);
//
//        Label label = new Label( "Hello" );
//        pane.setCenter(label);
//
//        this.stage.show();
    }

    public static void main( String[] args ) {
        Application.launch( args );
    }


    public void run() {
        new Browser().load( this.url ); }
}