package core;

import java.net.URL;

/**
 * Created by takayukihoshino on 11/7/16.
 */
public class RecipeHeader {

    private String publisher;
    private URL f2f_url;
    private String title;
    private URL source_url;
    private String recipe_id;
    private URL image_url;
    private double social_rank;
    private URL publisher_url;

    // Getters and setters

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher( String publisher ) {
        this.publisher = publisher;
    }

    public URL getF2f_url() {
        return f2f_url;
    }
    public void setF2f_url( URL f2f_url ) {
        this.f2f_url = f2f_url;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle( String title ) {
        this.title = title;
    }

    public URL getSource_url() {
        return source_url;
    }
    public void setSource_url( URL source_url ) {
        this.source_url = source_url;
    }

    public String getRecipe_id() {
        return recipe_id;
    }
    public void setRecipe_id( String recipe_id ) {
        this.recipe_id = recipe_id;
    }

    public URL getImage_url() {
        return image_url;
    }
    public void setImage_url( URL image_url ) {
        this.image_url = image_url;
    }

    public double getSocial_rank() {
        return social_rank;
    }
    public void setSocial_rank( double social_rank ) {
        this.social_rank = social_rank;
    }

    public URL getPublisher_url() {
        return publisher_url;
    }
    public void setPublisher_url( URL publisher_url ) {
        this.publisher_url = publisher_url;
    }


    @Override
    public String toString() {
        StringBuffer s = new StringBuffer()
                .append( "Publisher: " + this.publisher )
                .append( " / Food2Fork URL: " + this.f2f_url )
                .append( " / Title: " + this.title )
                .append( " / Source URL: " + this.source_url )
                .append( " / Recipe ID: " + this.recipe_id )
                .append( " / Image URL: " + this.image_url )
                .append( " / Social Rank: " + this.social_rank )
                .append( " / Publisher URL: " + this.publisher_url );
        return s.toString();
    }
}
