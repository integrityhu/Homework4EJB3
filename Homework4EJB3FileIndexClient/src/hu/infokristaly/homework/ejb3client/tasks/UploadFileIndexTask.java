/**
 * 
 */
package hu.infokristaly.homework.ejb3client.tasks;

import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemIndexService;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.ILocationInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IMediaInfo;
import hu.infokristaly.homework.ejb3client.utils.RecursiveScannerDirWithNIO;

/**
 * @author pzoli
 *
 */
public class UploadFileIndexTask {

    private IFileSystemIndexService fileInfoService;
    
    public UploadFileIndexTask(IFileSystemIndexService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }
    
    private String rootPath;
    private String place;
    private String mediaName;
    private String fileSystemName;
    
    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getFileSystemName() {
        return fileSystemName;
    }

    public void setFileSystemName(String fileSystemName) {
        this.fileSystemName = fileSystemName;
    }

    public void startIndexing() {
        ILocationInfo locationInfo = fileInfoService.getLocationInfo(place);
        IMediaInfo mediaInfo = fileInfoService.getMediaInfo(mediaName);
        IFileSystemInfo fileSystemInfo = fileInfoService.createFileSystemInfo(rootPath, fileSystemName, mediaInfo, locationInfo);
        RecursiveScannerDirWithNIO scanner = new RecursiveScannerDirWithNIO(fileInfoService);
        scanner.setFileSystemInfo(fileSystemInfo);
        scanner.start();
        try {
            scanner.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
