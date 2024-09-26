package dk.lyngby.dao;

import dk.lyngby.model.Hotel;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * @author Rouvi
 */


public class HotelDAO implements IDAO<Hotel> {
    private final EntityManagerFactory emf;

    public HotelDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Hotel hotel) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(hotel);
            em.getTransaction().commit();

        }
    }

    @Override
    public Hotel getById(long id) {
        try (var em = emf.createEntityManager()) {
            return em.find(Hotel.class, id);
        }
    }

    @Override
    public List<Hotel> getAll() {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
        }
    }

    @Override
    public void update(Hotel hotel, Hotel newHotel) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            hotel.setName(newHotel.getName());
            hotel.setAddress(newHotel.getAddress());
            hotel.setRooms(newHotel.getRooms());
            em.merge(hotel);
            em.getTransaction().commit();

        }
    }

    @Override
    public void delete(long id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            var hotel = em.find(Hotel.class, id);
            em.remove(hotel);
            em.getTransaction().commit();

        }
    }


}
