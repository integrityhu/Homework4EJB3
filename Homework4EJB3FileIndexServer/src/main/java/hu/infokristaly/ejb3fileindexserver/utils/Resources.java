/**
 * 
 */
package hu.infokristaly.ejb3fileindexserver.utils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author pzoli
 *
 */
public class Resources {

    @Produces
    @PersistenceContext
    private EntityManager em;

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
       return FacesContext.getCurrentInstance();
    }

}
