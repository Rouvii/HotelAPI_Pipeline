package dk.lyngby.routes;
import dk.lyngby.routes.RoomRoute;
import dk.lyngby.routes.HotelRoute;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

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
