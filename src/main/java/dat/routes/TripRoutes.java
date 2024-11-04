package dat.routes;

import dat.controllers.TripController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class TripRoutes {


    private final TripController tripController = new TripController();

    protected EndpointGroup getRoutes() {


        return () -> {
            // get trips by id
            get("/{id}", tripController::read);
            // get all trips
            get("/", tripController::readAll);
            // create a trip
            post("/", tripController::create);
            // update a trip
            put("/{id}", tripController::update);
            // delete a trip
            delete("/{id}", tripController::delete);
            // add a guide to a trip
            post("/{id}/guide/{guideId}", tripController::addGuideToTrip);
            // get a trip by guide is
            get("/guide/{guideId}", tripController::getTripsByGuide);


        };
    }
}

