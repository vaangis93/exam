package dat.routes;


import dat.enums.Role;
import dat.security.routes.SecurityRoutes;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {


    private final SecurityRoutes securityRoutes = new SecurityRoutes();

    private final TripRoutes tripRoutes = new TripRoutes();

    public EndpointGroup getRoutes() {
        return () -> {

            path("/trips", tripRoutes.getRoutes());
        };
    }
}
