/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.back.model;

import java.io.Serializable;

import hu.infokristaly.ejb3fileindexserver.interfaces.IMediaInfo;

/**
 * @author pzoli
 *
 */
public class MediaInfo implements IMediaInfo, Serializable {

    private static final long serialVersionUID = 8893133303565000844L;

    private Long id;
    
    private String name;
        
    private String comment;

    public MediaInfo() {
    }

    public MediaInfo(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
