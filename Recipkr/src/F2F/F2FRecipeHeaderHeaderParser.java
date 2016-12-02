package F2F;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import core.RecipeDataSource;
import core.RecipeHeader;
import core.RecipeHeaderParser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by takayukihoshino on 11/8/16.
 */
public class F2FRecipeHeaderHeaderParser implements RecipeHeaderParser {

    final static int ERROR_RECIPE_COUNT = -1;

    private RecipeDataSource dataSource;
    private boolean isJson;
    private String stringToParse;
    private ArrayList<RecipeHeader> allRecipeHeaders;
    private boolean initialized;
    private int nextRecipeIndex;
    private boolean allowReinitializing;

    public F2FRecipeHeaderHeaderParser( RecipeDataSource dataSource, boolean isJson, boolean allowReinitializing ) {

        this.dataSource = dataSource;
        this.isJson = isJson;
        this.stringToParse = null;
        this.allRecipeHeaders = null;
        this.initialized = false;
        this.allowReinitializing = allowReinitializing;

    }

    /***
     * initialize()
     *
     * Set up a string to be searched.
     * @param o string to be searched
     * @return true if initializing was successful
     */
    public boolean initialize( Object o ) {

        if ( this.initialized ) {
            if ( this.allowReinitializing == false ) {
                return false;
            }
        }
        if ( !( o instanceof String ) ) {
            return false;
        }
        this.stringToParse = (String) o;
        if ( !isValidJson( this.stringToParse ) ) {
            return false;
        }
        this.nextRecipeIndex = 0;
        if ( (this.allRecipeHeaders = buildRecipeHeaderList()) == null ) {
            return false;
        }
        this.initialized = true;
        return true;

    }


    /***
     * isJson()
     *
     * Return true if the target string for this parser is in the JSON format.
     *
     * @return
     */
    public boolean isJson() {
        return this.isJson;
    }


    /***
     * getNumberOfRecipeHeaders()
     *
     * Return the actual number of recipe headers found in the target string.
     * @return
     */
    public int getNumberOfRecipeHeaders() {
        return this.allRecipeHeaders.size();
    }


    /***
     * getNextRecipeHeader()
     *
     * Return a RecipeHeader object based on the next recipe header in the target string.
     * @return
     */
    public RecipeHeader getNextRecipeHeader() {

        RecipeHeader nextRecipeHeader;
        if ( this.nextRecipeIndex++ < this.allRecipeHeaders.size() ) {
            nextRecipeHeader = this.allRecipeHeaders.get( nextRecipeIndex );
        } else {
            nextRecipeHeader = null;
        }
        return nextRecipeHeader;

    }


    /***
     * moreRecipeHeaders()
     *
     * Return true if the serial access to the recipe headers in the target
     * @return
     */
    public boolean moreRecipeHeaders() {
        return this.nextRecipeIndex < this.allRecipeHeaders.size();
    }

    public boolean reset() {
        nextRecipeIndex = 0;
        return true;
    }


    public RecipeDataSource getDataSource() {
        return this.dataSource;
    }


    public ArrayList<RecipeHeader> getAllRecipeHeaders() {
        return this.allRecipeHeaders;
    }


    private ArrayList<RecipeHeader> buildRecipeHeaderList() {

        ArrayList<RecipeHeader> recipes = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree( stringToParse );
            int recipeCount = getRecipeCountFromHeader( rootNode );
            if ( recipeCount != ERROR_RECIPE_COUNT ) {
                recipes = readAllRecipeHeaders();
                if ( recipes.size() != recipeCount ) {
                    System.err.println( "Inconsistent recipe header data: " +
                            "Source header shows " + recipeCount +
                            "recipe headers but actual header count is " +
                            recipes.size() );
                }
            }
        } catch ( Exception e ) {
            System.err.println( "Exception: " + e.getMessage() );
        }

        return recipes;

    }


    /***
     * getRecipeCountFromHeader()
     *
     * Return the integer value of "count" field in the given json.
     * @param rootNode
     * @return
     */
    private int getRecipeCountFromHeader( JsonNode rootNode ) {

        int recipeCount = ERROR_RECIPE_COUNT;
        try {
            JsonNode countNode = rootNode.get( "count" );
            if ( countNode != null ) {
                recipeCount = countNode.asInt( ERROR_RECIPE_COUNT );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return recipeCount;

    }


    /***
     * readAllRecipeHeaders()
     *
     * Read an array of JsonNodes representing recipe entries, create an arraylist of
     * Recipe objects, and return the list.
     * @return
     */
    public ArrayList<RecipeHeader> readAllRecipeHeaders() {
        // TODO Implement
        ArrayList<RecipeHeader> recipeHeaders = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree( this.stringToParse );
            ArrayNode recipeListNode = (ArrayNode)rootNode.get( "recipes" );
            recipeListNode.elements().forEachRemaining( header -> {
                recipeHeaders.add( getARecipeHeader( header ) );
            });
            recipeHeaders.forEach( System.out::println );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return recipeHeaders;

    }


    /***
     * getARecipeHeader()
     *
     * Read the given json node, return  RecipeHeader object based on it.
     *
     * @param aRecipeHeaderNode
     * @return
     */
    private RecipeHeader getARecipeHeader ( JsonNode aRecipeHeaderNode ) {

        RecipeHeader aRecipeHeader = new RecipeHeader();

        URL f2fURL = null;
        URL sourceURL = null;
        URL imageURL = null;
        URL publisherURL = null;

        String publisherName = aRecipeHeaderNode.findPath( "publisher" ).asText();
        String title = aRecipeHeaderNode.findPath( "title" ).asText();
        String recipeID = aRecipeHeaderNode.findPath( "recipe_id" ).asText();
        double socialRank = aRecipeHeaderNode.findPath( "social_rank" ).asDouble();

        try {
            f2fURL = new URL( aRecipeHeaderNode.findPath( "f2f_url" ).asText() );
            sourceURL = new URL( aRecipeHeaderNode.findPath( "source_url" ).asText() );
            imageURL = new URL( aRecipeHeaderNode.findPath( "image_url" ).asText() );
            publisherURL = new URL( aRecipeHeaderNode.findPath( "publisher_url" ).asText() );
        } catch ( MalformedURLException e ) {
            System.err.println( "MalformedURLException: " + e.getMessage() );
            e.printStackTrace();
        }

        aRecipeHeader.setPublisher( publisherName );
        aRecipeHeader.setTitle( title );
        aRecipeHeader.setRecipe_id( recipeID );
        aRecipeHeader.setSocial_rank( socialRank );
        aRecipeHeader.setF2f_url( f2fURL );
        aRecipeHeader.setSource_url( sourceURL );
        aRecipeHeader.setImage_url( imageURL );
        aRecipeHeader.setPublisher_url( publisherURL );

        return aRecipeHeader;
    }


    /***
     * isValidJson()
     *
     * Return true or false, depending on whether the given json string is
     * syntactically valid.
     *
     * @param json
     * @return
     */
    private boolean isValidJson( final String json ) {

        try {
            new ObjectMapper().readTree( json );
            return true;
        } catch( IOException e ) {
            System.err.println( "IOException: " + e.getMessage() );
            return false;
        }

    }


}
