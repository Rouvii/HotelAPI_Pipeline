package dk.lyngby.routes;
import dk.lyngby.controller.HotelController;
import dk.lyngby.routes.RoomRoute;
import dk.lyngby.routes.HotelRoute;

import dk.lyngby.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Routes {


private final RoomRoute roomRoute = new RoomRoute();
private final HotelRoute hotelRoutes = new HotelRoute();



  public EndpointGroup getApiRoutes() {
    return () -> {
      path("/room", roomRoute.getRoomRoutes());
        path("/hotel", hotelRoutes.getHotelRoutes());


    };
  }



}
