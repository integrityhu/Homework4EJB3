/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.interfaces;

/**
 * @author pzoli
 *
 */
public interface ILocationInfo {

    public Long getId();

    public void setId(Long id);

    public String getPlace();

    public void setPlace(String place);

    public String getComment();

    public void setComment(String comment);
}
