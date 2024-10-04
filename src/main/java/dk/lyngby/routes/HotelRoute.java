package dk.lyngby.routes;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.HotelController;
import dk.lyngby.dao.HotelDAO;
import dk.lyngby.dao.RoomDAO;
import dk.lyngby.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;


import dk.lyngby.security.controllers.SecurityController;


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

    SecurityController securityController = SecurityController.getInstance();
    public EndpointGroup getHotelRoutes(){
        return () -> {
            get("/", hotelController::getAllHotels);
            get("/find/{id}", hotelController::getHotelById, Role.ANYONE);
            before(securityController.authenticate()); // check if there is a valid token in the header
            post("/", hotelController::createHotel, Role.ADMIN);
            put("/update/{id}", hotelController::updateHotel, Role.ADMIN);
            delete("/{id}", hotelController::deleteHotel, Role.ADMIN);
        };
    }

}
