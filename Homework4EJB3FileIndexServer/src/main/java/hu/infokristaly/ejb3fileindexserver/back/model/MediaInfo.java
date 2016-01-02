/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.back.model;

import java.io.Serializable;

import hu.infokristaly.ejb3fileindexserver.interfaces.IMediaInfo;

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
@Remote(IMediaInfo.class)
@NamedQuery (            
        name="media-info",
        query="SELECT l FROM MediaInfo l WHERE l.name = :name"
      )
public class MediaInfo implements IMediaInfo, Serializable {

    private static final long serialVersionUID = 8893133303565000844L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Basic
    private String name;
        
    @Lob
    private String comment;

    public MediaInfo() {
    }

    public MediaInfo(String name) {
        this.name = name;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
