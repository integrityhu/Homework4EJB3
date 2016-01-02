/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.interfaces;

import java.util.Date;

/**
 * @author pzoli
 *
 */
public interface IFileSystemInfo {

    public Long getId();

    public void setId(Long id);

    public String getPath();

    public void setPath(String path);

    public String getName();

    public void setName(String name);

    public IMediaInfo getMediaInfo();

    public void setMediaInfo(IMediaInfo mediaInfo);

    public ILocationInfo getLocationInfo();

    public void setLocationInfo(ILocationInfo locationInfo);

    public Date getLastModified();

    public void setLastModified(Date lastModified);

    public Date getUploadDate();

    public void setUploadDate(Date uploadDate);

    public String getComment();

    public void setComment(String comment);

}
