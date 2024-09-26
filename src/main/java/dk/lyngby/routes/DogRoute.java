package dk.lyngby.routes;


import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.DogControllerImpl;
import dk.lyngby.dao.DogDaoImpl;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class DogRoute {
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private static final DogDaoImpl dogDaoImpl = new DogDaoImpl(emf);
    private static final DogControllerImpl dogController = new DogControllerImpl(dogDaoImpl);
    public EndpointGroup getDogRoutes() {
        return () -> {
            get("/", dogController::getAllDogs);
            get("/{id}", dogController::getDogById);
            post("/", dogController::createDog);
            put("/{id}", dogController::updateDog);
            delete("/{id}", dogController::deleteDog);
        };
    }

}
