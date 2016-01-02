/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.interfaces;


import java.util.Date;

/**
 * @author pzoli
 *
 */
public interface IFileSystemIndexService {
    
    public ILocationInfo getLocationInfo(String place);
    public IMediaInfo getMediaInfo(String name);
    public IFileSystemInfo createFileSystemInfo(String rootPath, String fileSystemName, IMediaInfo mediaInfo, ILocationInfo locationInfo);
    public IFileSystemDirInfo createFileSystemDirInfo(String path, Date lastModified, IFileSystemInfo fileSystemInfo, IFileSystemDirInfo parentDirInfo);
    public IFileSystemDirInfo createFileSystemDirInfo(IFileSystemDirInfo dirInfo);
    public IFileSystemFileInfo createFileSystemFileInfo(String fileName, Long size, Date lastModified, IFileSystemDirInfo parentDirInfo);
    public IFileSystemFileInfo createFileSystemFileInfo(IFileSystemFileInfo fileInfo);

}
