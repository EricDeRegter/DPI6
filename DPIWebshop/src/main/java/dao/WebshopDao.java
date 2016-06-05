package dao;

import model.Webshop;

import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
public interface WebshopDao {

    Webshop create(Webshop webshop) throws Exception;

    Webshop findById(Long id) throws Exception;

    Webshop findByName(String name) throws Exception;

    List<Webshop> findAll() throws  Exception;
}
