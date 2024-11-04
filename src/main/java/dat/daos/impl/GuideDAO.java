package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.GuideDTO;
import dat.entities.Guide;
import dat.entities.Trip;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GuideDAO implements IDAO<GuideDTO, Integer> {

    private static GuideDAO instance;
    private static EntityManagerFactory emf;

    // making an singleton instance of TripDAO becuase we only need one instance of it
    public static GuideDAO getInstance(EntityManagerFactory _emf) {
        if (_emf == null) {
            throw new IllegalArgumentException("EntityManagerFactory must be initialized before accessing TripDAO");
        }
        if (instance == null) {
            emf = _emf;
            instance = new GuideDAO();
        }
        return instance;
    }

    @Override
    public GuideDTO read(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            Guide guide = em.find(Guide.class, integer);
            return new GuideDTO(guide);
        }
    }

    @Override
    public List<GuideDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            List<GuideDTO> guides = em.createQuery("SELECT g FROM Guide g", GuideDTO.class).getResultList();
            em.getTransaction().commit();
            return guides;
        }
    }

    @Override
    public GuideDTO create(GuideDTO guideDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Guide guide = new Guide(guideDTO);
            em.persist(guide);
            em.getTransaction().commit();
            return new GuideDTO(guide);
        }
    }

    @Override
    public GuideDTO update(Integer integer, GuideDTO guideDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Guide guideBeforeChange = em.find(Guide.class, integer);
            guideBeforeChange.setId(guideDTO.getId());
            guideBeforeChange.setFirstName(guideDTO.getFirstName());
            guideBeforeChange.setLastName(guideDTO.getLastName());
            guideBeforeChange.setEmail(guideDTO.getEmail());
            guideBeforeChange.setPhone(guideDTO.getPhone());
            guideBeforeChange.setYearsOfExperience(guideDTO.getYearsOfExperience());

            List<Trip> trips = guideDTO.getTrips().stream() // TODO make cleaner------------------------
                    .map(tripDTO -> {
                        // Find existing trip by ID (if present), or create a new one
                        Trip trip = em.find(Trip.class, tripDTO.getId());
                        if (trip == null) {
                            trip = new Trip();
                        }
                        // Update or set trip fields
                        trip.setName(tripDTO.getName());
                        trip.setStartTime(tripDTO.getStartTime());
                        trip.setEndTime(tripDTO.getEndTime());
                        trip.setStartPosition(tripDTO.getStartPosition());
                        trip.setPrice(tripDTO.getPrice());
                        trip.setCategory(tripDTO.getCategory());
                        trip.setGuide(guideBeforeChange); // Set the guide relationship
                        return trip;
                    })
                    .collect(Collectors.toList());

            em.getTransaction().commit();
            return new GuideDTO(guideBeforeChange);
        }
    }

    @Override
    public void delete(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Guide guide = em.find(Guide.class, integer);
            em.remove(guide);
            em.getTransaction().commit();
        }

    }
}
