package core;

import java.util.ArrayList;

/**
 * Created by takayukihoshino on 11/8/16.
 */
public interface RecipeHeaderParser {

    boolean initialize ( Object stringToParse );

    int getNumberOfRecipeHeaders();

    // ArrayList<Recipe> searchForRecipeHeaders( Object keyword );

    // ArrayList<Recipe> getRecipeHeaderForID( Object recipeID );

    ArrayList<RecipeHeader> getAllRecipeHeaders();

    RecipeHeader getNextRecipeHeader();

    boolean moreRecipeHeaders();

    boolean reset();

    boolean isJson();

    RecipeDataSource getDataSource();

}
