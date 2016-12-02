package UI;

import javafx.application.HostServices;
import javafx.scene.web.WebView;

/**
 * Created by takayukihoshino on 11/18/16.
 */
public class RemoteResourceAccess {

    private HostServices hostServices;
    private WebView browser;

    public RemoteResourceAccess( HostServices hostServices,
                                 WebView browser ) {
        this.hostServices = hostServices;
        this.browser =  browser;
    }

    public HostServices getHostServices() {
        return this.hostServices;
    }

    public WebView getBrowser() {
        return this.browser;
    }

    public void openInBrowser( String url ) {
        hostServices.showDocument( url );
    }

    public void openInAnotherScene( String url ) {
        browser.getEngine().load( url );
    }

}
