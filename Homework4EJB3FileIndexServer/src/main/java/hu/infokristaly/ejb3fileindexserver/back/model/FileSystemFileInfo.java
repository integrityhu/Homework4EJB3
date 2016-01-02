/*
 * Copyright 2013 Integrity Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Zoltan Papp
 * 
 */
package hu.infokristaly.ejb3fileindexserver.back.model;

import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemDirInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemFileInfo;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
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

@Entity
@Remote(IFileSystemFileInfo.class)
public class FileSystemFileInfo implements IFileSystemFileInfo, Serializable {

    private static final long serialVersionUID = -290942946364948871L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    
    @Basic
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "directory_id", referencedColumnName = "id")
    private FileSystemDirInfo dirInfo;

    @Basic
    private Long size;
	
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	
    @Lob
	private String comment;
			
	public FileSystemFileInfo() {
		super();
	}   

	/**
     * Gets the FileInfo unique identifier.
     * 
     * @return the FileInfo unique identifier
     */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
     * Sets the FileInfo unique identifier.
     * 
     * @param id
     *            the new FileInfo unique identifier
     */
	@Override
	public void setId(Long id) {
		this.id = id;
	}   
	
	/**
     * Gets the file name used on file system.
     * 
     * @return the file name used on file system
     */
	@Override
	public String getFileName() {
		return this.fileName;
	}

	/**
     * Sets the file name used on file system.
     * 
     * @param fileName
     *            the new file name used on file system
     */
	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}   
	
	/**
     * Gets the file location path.
     * 
     * @return the file location path
     */
	@Override
	public FileSystemDirInfo getDirInfo() {
		return this.dirInfo;
	}

	/**
     * Sets the file location path.
     * 
     * @param path
     *            the new file location path
     */
	@Override
	public void setDirInfo(IFileSystemDirInfo path) {
		this.dirInfo = (FileSystemDirInfo)path;
	}   
	
    /**
     * Gets the file size.
     * 
     * @return the file size
     */
	@Override
	public Long getSize() {
		return this.size;
	}

	/**
     * Sets the file size.
     * 
     * @param size
     *            the new file size
     */
	@Override
	public void setSize(Long size) {
		this.size = size;
	}   
		
	/**
     * Gets the start date of upload method.
     * 
     * @return the start date of upload method
     */
	@Override
	public Date getLastModified() {
        return lastModified;
    }

    /**
     * Sets the start date of upload method.
     * 
     * @param uploadStartDate
     *            the new start date of upload method
     */
	@Override
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Gets the end date of upload method.
     * 
     * @return the end date of upload method
     */
	@Override
    public Date getUploadDate() {
		return this.uploadDate;
	}

	/**
     * Sets the end date of upload method.
     * 
     * @param uploadDate
     *            the new end date of upload method
     */
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

    /**
     * Gets the size for human reader.
     * 
     * @return the size for human reader
     */
	@Override
    public String getSizeForHumanReader() {
        if (getSize() == null) {
            return null;
        }
        
        BigDecimal resultSize = new BigDecimal(getSize());
        String sizeUnitForHumanReader = "byte";
        if (resultSize.longValue() > (1024)) {
            resultSize = resultSize.divide(BigDecimal.valueOf(1024));
            sizeUnitForHumanReader = "KByte";
            if (resultSize.longValue() > 1024) {
                resultSize = resultSize.divide(BigDecimal.valueOf(1024));
                sizeUnitForHumanReader = "MByte";
                if (resultSize.longValue() > 1024) {
                    resultSize = resultSize.divide(BigDecimal.valueOf(1024));
                    sizeUnitForHumanReader = "GiB";
                }
            }
        }
        return String.format("%.2f %s", resultSize, sizeUnitForHumanReader);
    }
    
}
