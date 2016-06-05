package dao;

import model.Webshop;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
@Named
public class WebshopDaoJPA implements WebshopDao {

    @PersistenceContext(name = "dpiPU")
    private EntityManager entityManager;

    @Override
    public Webshop create(Webshop webshop) throws Exception {
        String errormessage = "Could not add webshop to the database";
        try {
            findByName(webshop.getName());
            throw new IllegalArgumentException("Webshop already exists");
        }
        catch(NoResultException ex) {
            try {
                entityManager.persist(webshop);
                return entityManager.find(Webshop.class, webshop.getId());
            } catch (Exception exception) {
                throw new Exception(errormessage, exception);
            }
        }
        catch (IllegalArgumentException ex) {
            throw new Exception(ex.getMessage(), ex);
        }catch (Exception ex) {
            throw new Exception(errormessage, ex);
        }


    }

    @Override
    public Webshop findById(Long id) throws Exception {
        try {
            return entityManager.find(Webshop.class, id);
        } catch (Exception ex) {
            throw new Exception("Could not find webshop in database", ex);
        }
    }

    @Override
    public Webshop findByName(String name) throws Exception {
        try {
            return (Webshop) entityManager.createNamedQuery("findWebshopByName").setParameter("name", name).getSingleResult();
        } catch (NoResultException ex) {
            throw new NoResultException("Webshop " + name + " does not exist");
        }
    }

    @Override
    public List<Webshop> findAll() throws Exception {
        try {
            CriteriaBuilder qb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Webshop> c = qb.createQuery(Webshop.class);
            c.from(Webshop.class);

            TypedQuery<Webshop> query = entityManager.createQuery(c);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not find webshops in database", ex);
        }
    }
}
