package dk.lyngby.routes;


import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {



private final RoomRoute roomRoute = new RoomRoute();
private final HotelRoute hotelRoute = new HotelRoute();


  public EndpointGroup getApiRoutes() {
    return () -> {
        path("room", roomRoute.getDogRoutes());
        path("hotel", hotelRoute.getDogRoutes());



    };
  }



}
