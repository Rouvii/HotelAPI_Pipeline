package dk.lyngby.routes;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.HotelController;
import dk.lyngby.dao.HotelDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

/**
 * @author Rouvi
 */


public class HotelRoute {
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private static final HotelDAO hotelDAO = new HotelDAO(emf);
    private static final HotelController hotelController = new HotelController(hotelDAO);
    public EndpointGroup getDogRoutes() {
        return () -> {
            get("/", hotelController::getAllHotels);
            get("/{id}", hotelController::getHotelById);
            post("/", hotelController::createHotel);
            put("/{id}", hotelController::updateHotel);
            delete("/{id}", hotelController::deleteHotel);
        };
    }

}
