package twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import commnication.Texting;
import commnication.TextingResult;
import myutils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by takayukihoshino on 1/18/17.
 */
public class MyTwilio implements Texting {

    private static final String ACCOUNT_SID = "AC8df62b99c552cde1464b8e581a40f301";
    private static final String AUTH_TOKEN = "cddb0916e26fd4a20272a37833d66a82";

    private static final String ORIGIN_PHONE_NUMBER = "+18729017461";

    public static final String TEST_PHONE_NUMBER = "+17734252824";
    public static final String TEST_TEXT = "こんにちは";

    private PhoneNumber originNumber;

    private ArrayList<PhoneNumber> numberList = new ArrayList<>();
    private ArrayList<String> textList = new ArrayList<>();

    public MyTwilio( String aNumber, String text ) {

        initialize();
        this.numberList.add( new PhoneNumber( aNumber ) );
        this.textList.add( text );

    }

    public MyTwilio( String aNumber, String[] textList ) {

        initialize();
        this.numberList.add( new PhoneNumber( aNumber) );
        this.textList = new ArrayList<>( Arrays.asList( textList) );

    }

    // TODO Implement TextMsg( multiple numbers, single msg ) ?

    // TODO Implement TextMsg( mulitple numbers, multiple msgs) ?

    public boolean initialize() {

        this.originNumber = new PhoneNumber( MyTwilio.ORIGIN_PHONE_NUMBER );
        Twilio.init( MyTwilio.ACCOUNT_SID, MyTwilio.AUTH_TOKEN );
        return true;
    }

    public TextingResult send() {
        return sendAMsg( this.originNumber, numberList.get(0), this.textList.get(0) );
    }

    public TextingResult[] sendAll() {

        assert( false ); return null;

    /*    return numberList.parallelStream()
                .collect( toNumber ->
                    textList.forEach( text ->
                            sendAMsg( this.originNumber, toNumber, text )
                    )
                )
                .collect( Collectors.toList() );*/
    }

    private TextingResult sendAMsg ( PhoneNumber originNumber,
                                     PhoneNumber destinationNumber,
                                     String text ) {
        Message message =
                Message.creator( destinationNumber, originNumber, text ).create();
        Utils.alert( "SID = " + message.getSid() );
        TextingResult result = new TextingResult(
                originNumber.toString(),
                destinationNumber.toString(),
                message.getSid(),
                message.getDateSent() );
        return result;
    }

}
