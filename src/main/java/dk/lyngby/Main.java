package dk.lyngby;


import dk.lyngby.config.AppConfig;
import dk.lyngby.config.HibernateConfig;
import dk.lyngby.dao.HotelDAO;
import dk.lyngby.dao.RoomDAO;
import dk.lyngby.model.Hotel;
import dk.lyngby.model.Room;
import jakarta.persistence.EntityManagerFactory;

public class Main {


    public static void main(String[] args) {


        AppConfig.startServer();

//        // Create EntityManagerFactory
//        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
//
//        // Create DAOs
//        HotelDAO hotelDAO = new HotelDAO(emf);
//        RoomDAO roomDAO = new RoomDAO(emf);
//
//        // Create and persist a Hotel
//        Hotel hotel = new Hotel();
//        hotel.setName("Test Hotel");
//        hotel.setAddress("123 Test Street");
//        hotelDAO.create(hotel);
//
//        // Create and persist a Room
//        Room room = new Room();
//        room.setRoomNumber(101);
//        room.setPrice(200);
//        room.setHotel(hotel); // Set the hotel
//        roomDAO.create(room);
//
//        // Print the created entities
//        System.out.println("Created Hotel: " + hotel);
//        System.out.println("Created Room: " + room);

    }



    }