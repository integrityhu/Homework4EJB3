/**
 * 
 */
package hu.infokristaly.homework.ejb3client.utils;

import hu.infokristaly.ejb3fileindexserver.back.model.*;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemDirInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemFileInfo;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemIndexService;
import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pzoli
 * 
 */
public class Finder extends SimpleFileVisitor<Path> {

    private final List<PathMatcher> matchers = new LinkedList<PathMatcher>();
    private int numMatches = 0;
    private IFileSystemIndexService service;
    private Path rootPath;
    private IFileSystemInfo fileSystemInfo;
    private IFileSystemDirInfo lastDir = null;

    private final HashMap<String, IFileSystemDirInfo> dirs = new HashMap<String, IFileSystemDirInfo>();

    private IFileSystemDirInfo getDirInfoByPath(Path find) {
        IFileSystemDirInfo result = dirs.get(find.toString());
        if (result == null) {
            Date lastAccessTime = getLastAccessDate(getBasicAttributes(find));
            result = service.createFileSystemDirInfo(find.toString(), lastAccessTime, fileSystemInfo, lastDir);
            dirs.put(find.toString(), result);
        }
        return result;
    }

    /**
     * @param basicAttributes
     * @return
     */
    private Date getLastAccessDate(BasicFileAttributes basicAttributes) {
        if (basicAttributes != null) {
            return new Date(basicAttributes.lastAccessTime().toMillis());
        } else {
            return null;
        }
    }

    /**
     * @param find
     * @return
     * @throws IOException
     */
    private BasicFileAttributes getBasicAttributes(Path find) {
        BasicFileAttributes result = null;
        try {
            result = Files.readAttributes(find, BasicFileAttributes.class);
        } catch (IOException e) {

        }
        return result;
    }

    private IFileSystemDirInfo getLastPath(Path find) {
        if ((lastDir != null) && lastDir.getPath().toString().equals(find.toString())) {
            return lastDir;
        } else {
            return getDirInfoByPath(find);
        }
    }

    private FileSystemFileInfo generateFileInfo(Path f, BasicFileAttributes a) {
        FileSystemFileInfo fileInfo = new FileSystemFileInfo();
        fileInfo.setFileName(f.getFileName().toString());
        fileInfo.setSize(a.size());
        fileInfo.setLastModified(new Date(a.lastModifiedTime().toMillis()));
        fileInfo.setUploadDate(new Date());
        return fileInfo;
    }

    private FileSystemDirInfo generateDirInfo(Path f, BasicFileAttributes a) {
        FileSystemDirInfo dirInfo = new FileSystemDirInfo();
        dirInfo.setPath(f.toString());
        dirInfo.setLastModified(new Date(a.lastModifiedTime().toMillis()));
        dirInfo.setUploadDate(new Date());
        return dirInfo;
    }

    public Finder(IFileSystemIndexService service) {
        this.service = service;
    }

    private boolean checkMatchers(Path path) {
        boolean result = true;
        for (PathMatcher matcher : matchers) {
            if (!matcher.matches(path)) {
                result = false;
                break;
            }
        }
        return result;
    }

    // Compares the glob pattern against
    // the file or directory name.
    void find(Path file, BasicFileAttributes attrs) {

        Path name = file.getFileName();
        if (name != null && checkMatchers(name) && !isRoot(file)) {
            IFileSystemDirInfo currentPath = getLastPath(file.getParent());
            if (attrs.isDirectory()) {
                IFileSystemDirInfo dirInfo = generateDirInfo(file, attrs);
                if (currentPath != null) {
                    dirInfo.setParentDirInfo(currentPath);
                }
                dirInfo.setFileSystemInfo(fileSystemInfo);
                dirInfo = service.createFileSystemDirInfo(dirInfo);
                dirs.put(file.toString(), dirInfo);
                lastDir = dirInfo;
            } else {
                IFileSystemFileInfo fileInfo = generateFileInfo(file, attrs);
                fileInfo.setDirInfo(currentPath);
                fileInfo = service.createFileSystemFileInfo(fileInfo);
            }

            System.out.println(file);
        }
    }

    /**
     * @param file
     * @param rootPath2
     * @return
     */
    private boolean isRoot(Path file) {
        try {
            return Files.isSameFile(file, rootPath);
        } catch (IOException e) {
            return true;
        }
    }

    // Prints the total number of
    // matches to standard out.
    void done() {
        System.out.println("Matched: " + numMatches);
    }

    // Invoke the pattern matching
    // method on each file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        find(file, attrs);
        return FileVisitResult.CONTINUE;
    }

    // Invoke the pattern matching
    // method on each directory.
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        find(dir, attrs);
        FileVisitResult result = attrs.isSymbolicLink() ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
        return result;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    /**
     * @param string
     */
    public void addMatcher(String pattern) {
        matchers.add(FileSystems.getDefault().getPathMatcher("glob:" + pattern));
    }

    public IFileSystemInfo getFileSystemInfo() {
        return fileSystemInfo;
    }

    public void setFileSystemInfo(IFileSystemInfo fileSystemInfo) {
        this.fileSystemInfo = fileSystemInfo;
        this.rootPath = new File(fileSystemInfo.getPath()).toPath();
    }

}
