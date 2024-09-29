package dk.lyngby.routes;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.HotelController;
import dk.lyngby.dao.HotelDAO;
import dk.lyngby.dao.RoomDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;
/**
 * Purpose:
 *
 * @author: Kevin Løvstad Schou
 */
public class HotelRoute {

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    private final HotelDAO hotelDAO = new HotelDAO(emf);

    private final RoomDAO roomDAO = new RoomDAO(emf);
    private final HotelController hotelController = new HotelController(hotelDAO,roomDAO);

    public EndpointGroup getHotelRoutes(){
        return () -> {
            get("/", hotelController::getAllHotels);
            get("/find/{id}", hotelController::getHotelById);
            post("/", hotelController::createHotel);
            put("/update/{id}", hotelController::updateHotel);
            delete("/{id}", hotelController::deleteHotel);
        };
    }

}
