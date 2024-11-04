package dat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dat.config.HibernateConfig;

import dat.daos.impl.TripDAO;
import dat.dtos.TripDTO;
import dat.entities.Guide;
import dat.entities.Trip;
import dat.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TripController {
    TripDAO dao = new TripDAO();
//    ObjectMapper objectMapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(TripController.class);


    public TripController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("exam");
        this.dao = TripDAO.getInstance(emf);
    }



    public void read(Context ctx) throws ApiException {
        int id = Integer.parseInt(ctx.pathParam("id"));
        TripDTO tripDTO = dao.read(id);

        if (tripDTO != null) {
            ctx.json(tripDTO);
            ctx.status(200); // HTTP 200 OK
            logger.info("Trip found: {}", tripDTO);
        } else {
            ctx.status(404); // HTTP 404 Not Found
            throw new ApiException(404, "Trip not found", LocalDateTime.now());
        }
    }


    public void readAll(Context ctx) throws ApiException {
        List<TripDTO> trips = dao.readAll();
        if (trips.isEmpty()) {
            ctx.status(404); // HTTP 404 Not Found
            throw new ApiException(404, "No trips found", LocalDateTime.now());
        } else {
            ctx.json(trips);
            ctx.status(200); // HTTP 200 OK
        }
    }


    public void create(Context ctx) throws ApiException {
        if (ctx.body().isEmpty()) {
            ctx.status(400); // HTTP 400 Bad Request
            throw new ApiException(400, "No trip data in request body", LocalDateTime.now());
        }

        TripDTO tripDTO = ctx.bodyAsClass(TripDTO.class);
        tripDTO = dao.create(tripDTO);
        ctx.json(tripDTO);
        ctx.status(201); // HTTP 201 Created
        logger.info("Created trip: {}", tripDTO);
    }


    public void update(Context ctx) throws ApiException {
        if (ctx.body().isEmpty()) {
            ctx.status(400); // HTTP 400 Bad Request
            throw new ApiException(400, "No trip data in request body", LocalDateTime.now());
        }

        int id = Integer.parseInt(ctx.pathParam("id"));
        TripDTO tripDTO = ctx.bodyAsClass(TripDTO.class);
        tripDTO = dao.update(id, tripDTO);
        ctx.json(tripDTO);
        ctx.status(200); // HTTP 200 OK
        logger.info("Updated trip: {}", tripDTO);
    }


    public void delete(Context ctx) throws ApiException {
        int id = Integer.parseInt(ctx.pathParam("id"));
        dao.delete(id);
        ctx.status(204); // HTTP 204 No Content
        logger.info("Deleted trip with ID: {}", id);
    }

    public void addGuideToTrip(Context ctx) {
        int tripId = Integer.parseInt(ctx.pathParam("id"));
        int guideId = Integer.parseInt(ctx.pathParam("guideId"));
        dao.addGuideToTrip(tripId, guideId);
        ctx.status(200); // HTTP 200 OK
        logger.info("Added guide with ID {} to trip with ID {}", guideId, tripId); // {} is a placeholder for the 2 arguments in the end of the code line
    }

    public void getTripsByGuide(Context ctx) {
        int guideId = Integer.parseInt(ctx.pathParam("guideId"));
        List<TripDTO> trips = dao.getTripsByGuide(guideId);
        ctx.json(trips);
        ctx.status(200); // HTTP 200 OK
        logger.info("Trips found for guide with ID {}", guideId);

        }

}// end class









