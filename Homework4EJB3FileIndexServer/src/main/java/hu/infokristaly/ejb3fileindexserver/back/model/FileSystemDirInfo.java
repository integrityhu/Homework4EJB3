/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.back.model;

import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemDirInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemInfo;

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
@Remote(IFileSystemDirInfo.class)
public class FileSystemDirInfo implements IFileSystemDirInfo, Serializable {

    private static final long serialVersionUID = -5705026013370690849L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Lob
    private String path;
    
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    
    @ManyToOne
    @JoinColumn(name = "filesystem_id", referencedColumnName = "id")
    private FileSystemInfo fileSystemInfo;

    @ManyToOne
    @JoinColumn(name = "directory_id", referencedColumnName = "id")
    private FileSystemDirInfo parentDirInfo;
    
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
    public IFileSystemInfo getFileSystemInfo() {
        return fileSystemInfo;
    }

    @Override
    public void setFileSystemInfo(IFileSystemInfo fileSystemInfo) {
        this.fileSystemInfo = (FileSystemInfo)fileSystemInfo;
    }

    @Override
    public IFileSystemDirInfo getParentDirInfo() {
        return parentDirInfo;
    }

    @Override
    public void setParentDirInfo(IFileSystemDirInfo parentDirInfo) {
        this.parentDirInfo = (FileSystemDirInfo)parentDirInfo;
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
