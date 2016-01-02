/**
 * 
 */
package hu.infokristaly.homework.ejb3fileindexingserver.tests;

import org.junit.Test;

import hu.infokristaly.ejb3fileindexserver.front.service.FileSystemIndexService;

/**
 * @author pzoli
 *
 */
public class PersistenceTests {

    @Test
    public void TestPersist() {
        FileSystemIndexService indexService = new FileSystemIndexService();
        indexService.getLocationInfo("nagy táska");
    }
}
