/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.back.model;

import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.ILocationInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IMediaInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pzoli
 *
 */
public class FileSystemInfo implements IFileSystemInfo, Serializable {

    private static final long serialVersionUID = 6858574202612739501L;

    private Long id;
    
    private String path;
    
    private String name;
    
    private MediaInfo mediaInfo;
    
    private LocationInfo locationInfo;
    
    private Date lastModified;
    
    private Date uploadDate;
    
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaInfo getMediaInfo() {
        return mediaInfo;
    }

    public void setMediaInfo(IMediaInfo mediaInfo) {
        this.mediaInfo = (MediaInfo)mediaInfo;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(ILocationInfo locationInfo) {
        this.locationInfo = (LocationInfo)locationInfo;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
