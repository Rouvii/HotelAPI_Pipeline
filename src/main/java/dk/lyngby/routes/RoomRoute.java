package dk.lyngby.routes;


import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.HotelController;
import dk.lyngby.controller.RoomController;
import dk.lyngby.dao.HotelDAO;
import dk.lyngby.dao.RoomDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

/**
 * @author Rouvi
 */


public class RoomRoute {
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private static final RoomDAO roomDAO = new RoomDAO(emf);
    private static final RoomController roomController = new RoomController(roomDAO);

    public EndpointGroup getDogRoutes() {
        return () -> {
            get("/", roomController::getAllRooms);
            get("/{id}", roomController::getRoomById);
            post("/", roomController::createRoom);
            put("/{id}", roomController::updateRoom);
            delete("/{id}", roomController::deleteRoom);
        };
    }
}