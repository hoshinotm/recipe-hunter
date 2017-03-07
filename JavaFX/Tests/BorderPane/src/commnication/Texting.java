package commnication;

import java.util.HashMap;

/**
 * Created by takayukihoshino on 1/18/17.
 */
public interface Texting {

    boolean initialize();
    // boolean setOriginNumber();
    // boolean clearDestNumbers();
    // boolean addDestNumber();
    // boolean clearAllText();
    // boolean addText();
    TextingResult send();
    TextingResult[] sendAll();

}
