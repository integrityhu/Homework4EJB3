/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.back.model;

import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.ILocationInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IMediaInfo;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Remote;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author pzoli
 *
 */
@Entity
@Remote(IFileSystemInfo.class)
public class FileSystemInfo implements IFileSystemInfo, Serializable {

    private static final long serialVersionUID = 6858574202612739501L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Lob
    private String path;
    
    @Basic
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "mediainfo_id", referencedColumnName = "id")
    private MediaInfo mediaInfo;
    
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationInfo locationInfo;
    
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    
    @Lob
    private String comment;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
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
    public MediaInfo getMediaInfo() {
        return mediaInfo;
    }

    @Override
    public void setMediaInfo(IMediaInfo mediaInfo) {
        this.mediaInfo = (MediaInfo)mediaInfo;
    }

    @Override
    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    @Override
    public void setLocationInfo(ILocationInfo locationInfo) {
        this.locationInfo = (LocationInfo)locationInfo;
    }

    @Override
    public Date getLastModified() {
        return lastModified;
    }

    @Override
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public Date getUploadDate() {
        return uploadDate;
    }

    @Override
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
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
