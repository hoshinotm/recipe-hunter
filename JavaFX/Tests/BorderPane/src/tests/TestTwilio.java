package tests;

import twilio.MyTwilio;

/**
 * Created by takayukihoshino on 1/18/17.
 */
public class TestTwilio {

    public static void main( String[] args ) {
        MyTwilio twilio = new MyTwilio( "17734252824", "Hello again!" );
        twilio.send();
    }

}
