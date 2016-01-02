/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.interfaces;

import java.util.Date;

/**
 * @author pzoli
 *
 */
public interface IFileSystemDirInfo {

    public Long getId();

    public void setId(Long id);

    public String getPath();

    public void setPath(String path);

    public Date getLastModified();

    public void setLastModified(Date lastModified);

    public Date getUploadDate();

    public void setUploadDate(Date uploadDate);

    public IFileSystemInfo getFileSystemInfo();

    public void setFileSystemInfo(IFileSystemInfo fileSystemInfo);

    public IFileSystemDirInfo getParentDirInfo();

    public void setParentDirInfo(IFileSystemDirInfo parentDirInfo);

    public String getComment();

    public void setComment(String comment);

}
