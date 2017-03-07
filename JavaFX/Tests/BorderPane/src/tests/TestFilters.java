package tests;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by takayukihoshino on 11/9/16.
 */
public class TestFilters {

    public static HttpResponse httpResponseFilter( HttpResponse originalResponse ) {

        HttpResponse substituteResponse = null;
        try {
            ProtocolVersion version = originalResponse.getProtocolVersion();
            int statusCode = 200;
            Scanner fileScanner = new Scanner( new File("out/test_json_response.json") );
            InputStream in = IOUtils.toInputStream(fileScanner.nextLine(), "UTF-8");
            String reason = "";
            substituteResponse = new BasicHttpResponse( version, statusCode, reason );
            BasicHttpEntity substituteEntity = new BasicHttpEntity();
            substituteEntity.setContent( in );
            substituteResponse.setEntity( substituteEntity );
        } catch ( Exception e ) {
            System.err.println( "TestFilters.httpResponseFilter() caught Exception" );
            e.printStackTrace();
        }
//        return substituteResponse;
        return originalResponse;
    }

}
