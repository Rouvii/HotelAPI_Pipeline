package dk.lyngby.dao;

import dk.lyngby.model.Hotel;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * @author Rouvi
 */


public class HotelDAO extends AbstractDAO<Hotel> {


    public HotelDAO(EntityManagerFactory emf, Class<Hotel> entityClass) {
        super(emf, entityClass);
    }


}
