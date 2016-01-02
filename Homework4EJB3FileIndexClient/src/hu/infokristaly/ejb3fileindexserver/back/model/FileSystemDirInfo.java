/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.back.model;

import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemDirInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pzoli
 *
 */
public class FileSystemDirInfo implements IFileSystemDirInfo, Serializable {

    private static final long serialVersionUID = -5705026013370690849L;

    private Long id;
    
    private String path;
    
    private Date lastModified;
    
    private Date uploadDate;
    
    private FileSystemInfo fileSystemInfo;

    private FileSystemDirInfo parentDirInfo;
    
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

    public IFileSystemInfo getFileSystemInfo() {
        return fileSystemInfo;
    }

    public void setFileSystemInfo(IFileSystemInfo fileSystemInfo) {
        this.fileSystemInfo = (FileSystemInfo)fileSystemInfo;
    }

    public IFileSystemDirInfo getParentDirInfo() {
        return parentDirInfo;
    }

    public void setParentDirInfo(IFileSystemDirInfo parentDirInfo) {
        this.parentDirInfo = (FileSystemDirInfo)parentDirInfo;
    }

    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

}
