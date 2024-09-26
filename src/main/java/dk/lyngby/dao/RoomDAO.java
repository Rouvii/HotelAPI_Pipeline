package dk.lyngby.dao;

import dk.lyngby.model.Room;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * @author Rouvi
 */


public class RoomDAO extends AbstractDAO<Room> {


    public RoomDAO(EntityManagerFactory emf, Class<Room> entityClass) {
        super(emf, entityClass);
    }
}
