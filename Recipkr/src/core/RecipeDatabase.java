package core;

import F2F.F2FRecipeHeaderHeaderParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import myutils.ReportingUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import testing.TestFilters;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by takayukihoshino on 11/6/16.
 */
public class RecipeDatabase implements RecipeDataAccess {

    final static String JSON_RESPONSE_FILENAME = "out/json_response.json";

    final static String KEYWORD_TAG = "&q=";

    private String dataSourceName;
    private String dataSourceWebURL;
    private String querieURL;
    private String apiKey;
    private String keywords = "";

    public RecipeDatabase( RecipeDataSource dataSource ) {

        if ( dataSource == RecipeDataSource.FOOD2FORK ) {
            this.dataSourceName = RecipeDataSource.FOOD2FORK.getName();
            this.dataSourceWebURL = RecipeDataSource.FOOD2FORK.getWebURL();
            this.apiKey = RecipeDataSource.FOOD2FORK.getAPIKey();
            this.querieURL = RecipeDataSource.FOOD2FORK.getQuerieURL() + "?key=" + this.apiKey;
        } else {
            throw new IllegalArgumentException( "Unimplemented data source" );
        }

    }

    public String getSourceName() {
        return this.dataSourceName;
    }

    public String getSourceWebURL() {
        return this.dataSourceWebURL;
    }

    public int getNumberOfRecipeHeaders ( String key ) {
        // TODO Impelement getNumberOfRecipes()
        return 0;
    }

    public ArrayList<RecipeHeader> requestRecipeHeaders( int page ) {

        System.out.println( this.querieURL + keywords );

        ArrayList<RecipeHeader> recipeHeaders = null;
        try {
            // TODO Correct to read ALL pages and build a list based on all recipes
            // Repeat sendRecipeRequest()? How to detect end of recipes?
            HttpResponse httpResponse = sendRecipeHeaderRequest( this.  querieURL+KEYWORD_TAG+this.keywords );
            httpResponse = TestFilters.httpResponseFilter( httpResponse );
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            ReportingUtils.testingInfo( "Response Code : " + statusCode );
            if ( statusCode == 200 ) {   // Status code says okay
                String jsonString = getResponseString( httpResponse );
                saveStringToFile( jsonString, JSON_RESPONSE_FILENAME );
                RecipeHeaderParser parser = new F2FRecipeHeaderHeaderParser( RecipeDataSource.FOOD2FORK,
                                                            true, false);
                parser.initialize( jsonString );
                recipeHeaders = parser.getAllRecipeHeaders();
                ReportingUtils.testingInfo( recipeHeaders.size() + " recipes returned.");
            } else {
                System.out.println( "Aborting");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return recipeHeaders;

    }

    private HttpResponse sendRecipeHeaderRequest( String url ) {

//        final String url = "http://food2fork.com/api/search?key=2c73eabf0e5d3d8d3dc2b65bea9bf79f&q=sushi";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet( url );

        HttpResponse response = null;
        try {
            ReportingUtils.testingInfo( "RecipeDatabase.sendRecipeHeaderRequest(): URL = " + url );
            response = client.execute( request );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return response;

    }


    private String getResponseString( HttpResponse httpResponse ) {

        StringBuffer result = null;
        try {
            // TODO Latest error here
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader( httpResponse.getEntity().getContent() ) );
            result = new StringBuffer();
            String line;
            while ( (line = rd.readLine()) != null ) {
                result.append( line );
                System.out.println( line );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return result.toString();

    }


    private static boolean isValidJsonN( final String json ) throws IOException {
        try{
            new ObjectMapper().readTree(json);
            return true;
        } catch( JsonProcessingException e ){
            System.err.println( "JsonProcessingException: " + e.getMessage() );
            return false;
        }
    }


    public String getKeywords() {
        return this.keywords.replace("%20", " ");
    }

    public void addKeyword( String key ) {
        key = key.replace( " ", "%20" ).toLowerCase();
        if ( this.keywords.isEmpty() ) {
            this.keywords = key;
        } else if ( ! this.keywords.contains(key) ) {
            this.keywords += "%20" + key;
        }
    }

    public void clearKeywords() {
        this.keywords = "";
    }

    public boolean saveStringToFile( String string,
                                     String filename )
            throws IOException {

        FileWriter fWriter;
        PrintWriter pWriter = null;

        try {       //  PrintWriter does not throw exception
            fWriter = new FileWriter( filename );
            pWriter = new PrintWriter( fWriter );
            pWriter.write( string );
            pWriter.close();
        } catch ( IOException e ) {
            System.err.println( this.getClass().getName() + "() caught IOException" +
                                e.getMessage() );
            e.printStackTrace();
        }

        return pWriter.checkError();
    }



}
