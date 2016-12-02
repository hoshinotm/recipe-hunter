package core;

import java.util.Date;

/**
 * Created by takayukihoshino on 11/22/16.
 */
public class SearchCriteria {
    private String id;
    private String[] toInclude;
    private String[] toExclude;
    private Date fromDate;
    private Date toDate;

    public SearchCriteria( String[] toInclude, String[] toExclude ) {
        this.toInclude = toInclude;
        this.toExclude = toExclude;
        this.id = null;
        this.fromDate = null;
        this.toDate = null;
    }

    public SearchCriteria( String id ) {
        this.id = id;
        this.toInclude = null;
        this.toExclude = null;
        this.fromDate = null;
        this.toDate = null;
    }

    public SearchCriteria( Date fromDate, Date toDate ) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.id = null;
        this.toInclude = null;
        this.toExclude = null;
    }

    public String getId() {

        return id;
    }

    public void setId( String id ) {

        this.id = id;
    }

    public String[] getToInclude() {

        return toInclude;
    }

    public void setToInclude( String[] toInclude ) {

        this.toInclude = toInclude;
    }

    public String[] getToExclude() {

        return toExclude;
    }

    public void setToExclude( String[] toExclude ) {

        this.toExclude = toExclude;
    }

    public Date getFromDate() {

        return fromDate;
    }

    public void setFromDate( Date fromDate ) {

        this.fromDate = fromDate;
    }

    public Date getToDate() {

        return toDate;
    }

    public void setToDate( Date toDate ) {

        this.toDate = toDate;
    }
}
