/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.back.model;

import java.io.Serializable;

import hu.infokristaly.ejb3fileindexserver.interfaces.ILocationInfo;

/**
 * @author pzoli
 *
 */
public class LocationInfo implements ILocationInfo, Serializable {

    private static final long serialVersionUID = 3612748356422221642L;

    private Long id;
    
    private String place;
        
    private String comment;

    public LocationInfo() {
    }

    public LocationInfo(String place) {
        this.place = place;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
