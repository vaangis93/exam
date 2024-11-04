package dat.daos.impl;

import dat.daos.IDAO;
import dat.daos.ITripGuideDAO;
import dat.dtos.TripDTO;
import dat.entities.Guide;
import dat.entities.Trip;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TripDAO implements IDAO<TripDTO, Integer>, ITripGuideDAO {

    private static TripDAO instance;
    private static EntityManagerFactory emf;

    public static TripDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TripDAO();
        }
        return instance;
    }

    @Override
    public TripDTO read(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            Trip trip = em.find(Trip.class, integer);
            return new TripDTO(trip);
        }
    }

    @Override

    public List<TripDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t", Trip.class);
            List<Trip> trips = query.getResultList();
            em.getTransaction().commit();
            return trips.stream().map(TripDTO::new).collect(Collectors.toList());
        }
    }


    @Override
    public TripDTO create(TripDTO tripDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Trip trip = new Trip(tripDTO);
            em.persist(trip);
            em.getTransaction().commit();
            return new TripDTO(trip);
        }
    }

    @Override
    public TripDTO update(Integer integer, TripDTO tripDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Trip tripBeforeChange = em.find(Trip.class, integer);
            tripBeforeChange.setId(tripDTO.getId());
            tripBeforeChange.setName(tripDTO.getName());
            tripBeforeChange.setCategory(tripDTO.getCategory());
            tripBeforeChange.setPrice(tripDTO.getPrice());
            tripBeforeChange.setGuide(tripDTO.getGuide());
            tripBeforeChange.setStartPosition(tripDTO.getStartPosition());
            tripBeforeChange.setStartTime(tripDTO.getStartTime());
            tripBeforeChange.setEndTime(tripDTO.getEndTime());
            em.getTransaction().commit();

            return new TripDTO(tripBeforeChange);
        }
    }

    @Override
    public void delete(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Trip trip = em.find(Trip.class, integer);
            em.remove(trip);
            em.getTransaction().commit();
        }

    }

    @Override
    public void addGuideToTrip(int tripId, int guideId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Trip trip = em.find(Trip.class, tripId);
            trip.setGuide(em.find(Guide.class, guideId));
            em.getTransaction().commit();
        }
    }

    @Override
    public List<TripDTO> getTripsByGuide(int guideId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            List<TripDTO> tripDTOs = em.find(Guide.class, guideId).getTrips().stream()
                    .map(trip -> new TripDTO(
                            trip.getId(),
                            trip.getName(),
                            trip.getStartTime(),
                            trip.getEndTime(),
                            trip.getStartPosition(),
                            trip.getPrice(),
                            trip.getCategory()
                    ))
                    .collect(Collectors.toList());

            em.getTransaction().commit();
            return tripDTOs;
        }
    }
}
