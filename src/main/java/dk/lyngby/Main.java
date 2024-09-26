package dk.lyngby;


import dk.lyngby.config.AppConfig;
import dk.lyngby.config.HibernateConfig;
import dk.lyngby.dao.HotelDAO;
import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {


        AppConfig.startServer();

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        HotelDAO hotelDAO = new HotelDAO(emf);


    }




    }