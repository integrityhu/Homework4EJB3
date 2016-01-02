/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.front.service;

import java.io.Serializable;
import java.util.Date;

import hu.infokristaly.ejb3fileindexserver.back.model.FileSystemDirInfo;
import hu.infokristaly.ejb3fileindexserver.back.model.FileSystemFileInfo;
import hu.infokristaly.ejb3fileindexserver.back.model.FileSystemInfo;
import hu.infokristaly.ejb3fileindexserver.back.model.LocationInfo;
import hu.infokristaly.ejb3fileindexserver.back.model.MediaInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemIndexService;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemDirInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemFileInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.ILocationInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IMediaInfo;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author pzoli
 * 
 */
@Named
@Stateless
@Remote(IFileSystemIndexService.class)
public class FileSystemIndexService implements IFileSystemIndexService, Serializable {

    private static final long serialVersionUID = 8685914121290811299L;

    @Inject
    private EntityManager em;

    @Override
    public ILocationInfo getLocationInfo(String place) {
        ILocationInfo result = null;
        Query q = em.createNamedQuery("location-info");
        q.setParameter("place", place);
        try {
            result = (ILocationInfo) q.getSingleResult();
        } catch (Exception ex) {
            result = (ILocationInfo) new LocationInfo();
            result.setPlace(place);
            em.persist(result);
        }
        return result;
    }

    @Override
    public IMediaInfo getMediaInfo(String name) {
        IMediaInfo result = null;
        Query q = em.createNamedQuery("media-info");
        q.setParameter("name", name);
        try {
            result = (IMediaInfo) q.getSingleResult();
        } catch (Exception ex) {
            result = new MediaInfo();
            result.setName(name);
            em.persist(result);
        }
        return result;
    }

    @Override
    public IFileSystemInfo createFileSystemInfo(String rootPath, String fileSystemName, IMediaInfo mediaInfo, ILocationInfo locationInfo) {
        FileSystemInfo result = new FileSystemInfo();
        LocationInfo location = em.find(LocationInfo.class, locationInfo.getId());
        MediaInfo media = em.find(MediaInfo.class, mediaInfo.getId());
        result.setPath(rootPath);
        result.setName(fileSystemName);
        result.setMediaInfo(media);
        result.setLocationInfo(location);
        em.persist(result);
        return result;
    }

    @Override
    public IFileSystemDirInfo createFileSystemDirInfo(String path, Date lastModified, IFileSystemInfo fileSystemInfo, IFileSystemDirInfo parentDirInfo) {
        FileSystemDirInfo result = new FileSystemDirInfo();
        FileSystemInfo fileSystem = em.find(FileSystemInfo.class, fileSystemInfo.getId());
        if (parentDirInfo != null) {
            FileSystemDirInfo parentDir = em.find(FileSystemDirInfo.class, parentDirInfo.getId());
            result.setParentDirInfo(parentDir);
        }
        result.setPath(path);
        result.setFileSystemInfo(fileSystem);
        result.setLastModified(lastModified);
        result.setUploadDate(new Date());
        em.persist(result);
        return result;
    }

    @Override
    public IFileSystemDirInfo createFileSystemDirInfo(IFileSystemDirInfo dirInfo) {
        em.persist(dirInfo);
        return dirInfo;
    }

    @Override
    public IFileSystemFileInfo createFileSystemFileInfo(String fileName, Long size, Date lastModified, IFileSystemDirInfo dirInfo) {
        FileSystemFileInfo result = null;
        result = new FileSystemFileInfo();
        result.setFileName(fileName);
        result.setSize(size);
        result.setLastModified(lastModified);
        result.setUploadDate(new Date());
        em.persist(result);
        return result;
    }

    @Override
    public IFileSystemFileInfo createFileSystemFileInfo(IFileSystemFileInfo fileInfo) {
        em.persist(fileInfo);
        return fileInfo;
    }
}
