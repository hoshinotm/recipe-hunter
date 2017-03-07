package commnication;

import com.sun.scenario.effect.Offset;
import org.joda.time.DateTime;

import java.time.OffsetDateTime;
import java.time.OffsetTime;

/**
 * Created by takayukihoshino on 1/18/17.
 */
public class TextingResult {

    String originNumber;
    String destinationNumber;
    String id;
    DateTime dateTimeSent;

    public TextingResult( String originNumber, String destinationNumber, String id ) {
        this.originNumber = originNumber;
        this.destinationNumber = destinationNumber;
        this.id = id;
        this.dateTimeSent = DateTime.now();
    }

    public TextingResult( String originNumber,
                          String destinationNumber,
                          String id,
                          DateTime dateTimeSent ) {
        this.originNumber = originNumber;
        this.destinationNumber = destinationNumber;
        this.id = id;
        this.dateTimeSent = dateTimeSent;
    }

    public String getOriginNumber() {

        return originNumber;
    }

    public String getDestinationNumber() {

        return destinationNumber;
    }

    public String getId() {

        return id;
    }

    public DateTime getDateTimeSent() {

        return dateTimeSent;
    }

    @Override
    public String toString() {
        return "Origin: " + this.originNumber
                + "/ Destination: " + this.destinationNumber
                + "/ Date & Time: " + dateTimeSent
                + "/ SID: " + this.id
                ;
    }

}
