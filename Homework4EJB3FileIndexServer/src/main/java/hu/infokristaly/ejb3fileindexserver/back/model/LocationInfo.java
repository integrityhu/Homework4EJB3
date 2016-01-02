/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.back.model;

import java.io.Serializable;

import hu.infokristaly.ejb3fileindexserver.interfaces.ILocationInfo;

import javax.ejb.Remote;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;

/**
 * @author pzoli
 *
 */
@Entity
@Remote(ILocationInfo.class)
@NamedQuery (            
  name="location-info",
  query="SELECT l FROM LocationInfo l WHERE l.place = :place"
)
public class LocationInfo implements ILocationInfo, Serializable {

    private static final long serialVersionUID = 3612748356422221642L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Basic
    private String place;
        
    @Lob
    private String comment;

    public LocationInfo() {
    }

    public LocationInfo(String place) {
        this.place = place;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPlace() {
        return place;
    }

    @Override
    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

}
