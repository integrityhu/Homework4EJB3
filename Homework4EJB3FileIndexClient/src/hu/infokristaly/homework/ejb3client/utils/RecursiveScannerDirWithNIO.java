/**
 * 
 */
package hu.infokristaly.homework.ejb3client.utils;

import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemIndexService;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @author pzoli
 *
 */
public class RecursiveScannerDirWithNIO extends Thread {
    private String pattern = "*";
    private IFileSystemInfo fileSystemInfo;
    private IFileSystemIndexService service;
    
    public IFileSystemInfo getFileSystemInfo() {
        return fileSystemInfo;
    }

    public void setFileSystemInfo(IFileSystemInfo fileSystemInfo) {
        this.fileSystemInfo = fileSystemInfo;
    }

    public RecursiveScannerDirWithNIO(IFileSystemIndexService service) {
        this.service = service;
    }

    @Override
    public void run() {
        Path rootDirPath = Paths.get(fileSystemInfo.getPath());
        Finder finder = new Finder(service);
        finder.setFileSystemInfo(fileSystemInfo);
        finder.addMatcher(pattern);
        try {
            Date start = new Date();
            Files.walkFileTree(rootDirPath, finder);
            Date end = new Date();
            System.out.println(end.getTime()-start.getTime() + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            finder.done();
        }
    }

}
