package myutils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by takayukihoshino on 11/10/16.
 */

public class ErrorLog {
    private OutputStream outputStream1 = null;
    private OutputStream outputStream2 = null;

    public ErrorLog ( OutputStream outputStream1 ) {
        this.outputStream1 = outputStream1;
    }

    public ErrorLog ( OutputStream outputStream1, OutputStream outputStream2 ) {
        this.outputStream1 = outputStream1;
        this.outputStream2 = outputStream2;
    }
    public OutputStream getOutputStream() {
        return this.outputStream1;
    }

    public void setOutputStream( OutputStream outputStream1 ) {
        this.outputStream1 = outputStream1;
    }

    public void setOutputStreams( OutputStream outputStream1, OutputStream outputStream2 ) {
        this.outputStream1 = outputStream1;
        this.outputStream2 = outputStream2;
    }

    public boolean addOutputStream( OutputStream outputStream2 ) {
        boolean completed = true;
        if ( this.outputStream2 != null ) {
            try {
                this.outputStream2.flush();
                this.outputStream2.close();
                this.outputStream2 = outputStream2;
            } catch ( IOException e ) {
                System.err.println( "ErrorLog.write(): Caught IOException" + e.getMessage() );
                completed = false;
            }
        }
        return completed;
    }

    public boolean write( String entry ) {
        boolean completed = true;
        try {
            this.outputStream1.write( entry.concat( "\n" ).getBytes() );
            if ( outputStream2 != null ) {
                this.outputStream2.write( entry.concat( "\n" ).getBytes() );
            }
        } catch ( IOException e ){
            completed = false;
            System.err.println( "ErrorLog.write(): Caught IOException" + e.getMessage() );
        }
        return completed;
    }

}
