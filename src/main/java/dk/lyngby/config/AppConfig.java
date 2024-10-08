package dk.lyngby.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import dk.bugelhartmann.UserDTO;
import dk.lyngby.controller.ExceptionController;
import dk.lyngby.exception.ApiException;
import dk.lyngby.routes.Routes;
import dk.lyngby.security.controllers.SecurityController;
import dk.lyngby.security.routes.SecurityRoutes;
import dk.lyngby.util.ApiProps;
import dk.lyngby.util.Utils;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.security.RouteRole;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Set;


@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AppConfig {
    private static ExceptionController exceptionController = new ExceptionController();
   private static Routes routes = new Routes();

    private static ObjectMapper jsonMapper = new Utils().getObjectMapper();
    private static SecurityController securityController = SecurityController.getInstance();
    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);


    private static void setExceptionController(Javalin app) {
        app.exception(ApiException.class, exceptionController::apiExceptionHandler);
        app.exception(Exception.class, exceptionController::exceptionHandler);
    }

    private static void configuration(JavalinConfig config) {
        //Server
        config.router.contextPath = ApiProps.API_CONTEXT;
        //Plugin
        config.bundledPlugins.enableRouteOverview("/routes");

        config.bundledPlugins.enableDevLogging();
        //routes
        config.router.apiBuilder(routes.getApiRoutes());
        config.router.apiBuilder(SecurityRoutes.getSecuredRoutes());
        config.router.apiBuilder(SecurityRoutes.getSecurityRoutes());

    }

    public static void startServer() {
        var app = Javalin.create(AppConfig::configuration);
        app.beforeMatched(AppConfig::accessHandler);
        setExceptionController(app);
        setGeneralExceptionHandling(app);
        app.start(ApiProps.PORT);
        logger.info("Server started on port: " + ApiProps.PORT);


    }

    private static void setGeneralExceptionHandling(Javalin app) {
        app.exception(Exception.class, (e, ctx) -> {
            logger.error("An unhandled exception occurred", e.getMessage());
            ctx.json(Utils.convertErrorToJson(e.getMessage()));
        });
        app.exception(ApiException.class, (e, ctx) -> {
            ctx.status(e.getStatusCode());
            logger.warn("An API exception occurred: Code: {}, Message: {}", e.getStatusCode(), e.getMessage());
            ctx.json(Utils.convertErrorToJson(e.getMessage()));
        });
    }

    private static void accessHandler(Context ctx) {
        UserDTO user = ctx.attribute("user"); // the User was put in the context by the SecurityController.authenticate method (in a before filter on the route)
        Set<RouteRole> allowedRoles = ctx.routeRoles(); // roles allowed for the current route
        if (!securityController.authorize(user, allowedRoles)) {
            if (user != null){
                throw new ApiException(HttpStatus.FORBIDDEN.getCode(), "Unauthorized with roles: " + user.getRoles() + ". Needed roles are: " + allowedRoles);
            } else {
                throw new ApiException(HttpStatus.FORBIDDEN.getCode(), "You need to log in, dude!");
            }
        }
    }


}

