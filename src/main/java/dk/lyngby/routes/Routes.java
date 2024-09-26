package dk.lyngby.routes;


import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

  private final DogRoute dogRoute = new DogRoute();


  public EndpointGroup getApiRoutes() {
    return () -> {

        path("/dog", dogRoute.getDogRoutes());

    };
  }



}
