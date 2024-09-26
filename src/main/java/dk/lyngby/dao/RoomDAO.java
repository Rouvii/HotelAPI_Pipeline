package dk.lyngby.dao;

import dk.lyngby.model.Room;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * @author Rouvi
 */


public class RoomDAO implements IDAO<Room> {
    private final EntityManagerFactory emf;

    public RoomDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Room room) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();

        }
    }

    @Override
    public Room getById(long id) {
        try(var em = emf.createEntityManager()){
            return em.find(Room.class, id);
        }
    }

    @Override
    public List<Room> getAll() {
        try(var em = emf.createEntityManager()){
            return em.createQuery("SELECT r FROM Room r", Room.class).getResultList();
        }
    }

    @Override
    public void update(Room room, Room newRoom) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            room.setNumber(newRoom.getNumber());
            room.setPrice(newRoom.getPrice());
            em.merge(room);
            em.getTransaction().commit();

        }
    }

    @Override
    public void delete(long id) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Room room = em.find(Room.class, id);
            em.remove(room);
            em.getTransaction().commit();

        }
    }
}
