package dk.lyngby.routes;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.RoomController;
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
public class RoomRoute {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    private final RoomDAO roomDao = new RoomDAO(emf);
    private final HotelDAO hotelDao = new HotelDAO(emf);

    private final RoomController roomController = new RoomController(roomDao, hotelDao);

    public EndpointGroup getRoomRoutes(){
        return() -> {
            get("/", roomController::getAllRooms);
            get("/{id}", roomController::getRoomById);
            post("/", roomController::createRoom);
            put("/{id}", roomController::updateRoom);
            delete("/{id}", roomController::deleteRoom);
        };
    }
}
