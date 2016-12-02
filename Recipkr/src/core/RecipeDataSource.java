package core;

/**
 * Created by takayukihoshino on 11/6/16.
 */
public enum RecipeDataSource {

    FOOD2FORK( "Food2Fork",
                "www.food2fork.com",
                "http://food2fork.com/api/search",
                "2c73eabf0e5d3d8d3dc2b65bea9bf79f" );

    public final String name;
    public final String webURL;
    private final String querieURL;
    private final String apiKey;

    RecipeDataSource ( String name, String webURL, String querieURL, String apiKey ) {
        this.name = name;
        this.webURL = webURL;
        this.querieURL = querieURL;
        this.apiKey = apiKey;
    }

    public String getName() { return this.name; }

    public String getWebURL() { return this.webURL; }

    public String getQuerieURL(){ return this.querieURL; }

    protected String getAPIKey() {
        return this.apiKey;
    }

}

