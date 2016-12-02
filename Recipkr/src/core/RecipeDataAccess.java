package core;

import java.util.ArrayList;

/**
 * Created by takayukihoshino on 11/6/16.
 */
public interface RecipeDataAccess {
    String getSourceName();
    String getSourceWebURL();
    String getKeywords();
    void addKeyword( String key );
    void clearKeywords();
    ArrayList<RecipeHeader> requestRecipeHeaders ( int pageNumber );
    int getNumberOfRecipeHeaders ( String key );
}