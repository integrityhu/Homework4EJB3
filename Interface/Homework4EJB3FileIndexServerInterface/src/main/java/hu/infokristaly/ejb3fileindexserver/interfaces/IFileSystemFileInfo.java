/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.interfaces;

import java.util.Date;

public interface IFileSystemFileInfo {

    public Long getId();

    public void setId(Long id);
    
    public String getFileName();

    public void setFileName(String fileName);
    
    public IFileSystemDirInfo getDirInfo();

    public void setDirInfo(IFileSystemDirInfo path);
    
    public Long getSize();

    public void setSize(Long size);
        
    public Date getLastModified();

    public void setLastModified(Date lastModified);

    public Date getUploadDate();

    public void setUploadDate(Date uploadDate);
    
    public String getComment();

    public void setComment(String comment);

    public String getSizeForHumanReader();

}
