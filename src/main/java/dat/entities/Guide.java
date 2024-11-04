package dat.entities;


import dat.dtos.GuideDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "guide")
@Setter
@AllArgsConstructor
@ToString
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "years_of_experience")
    private int yearsOfExperience;

    @OneToMany(mappedBy = "guide" , cascade = CascadeType.PERSIST) // maybe add cascade = CascadeType.PERSIST and orphanRemoval = true
    @Column(name = "trips", nullable = true)
    private List<Trip> trips = new ArrayList<>();  // One guide can offer multiple trips


    // constructor to convert from DTO to entity
    public Guide(GuideDTO guideDTO) {
        this.id = guideDTO.getId();
        this.firstName = guideDTO.getFirstName();
        this.lastName = guideDTO.getLastName();
        this.email = guideDTO.getEmail();
        this.phone = guideDTO.getPhone();
        this.yearsOfExperience = guideDTO.getYearsOfExperience();
        List<Trip> trips = guideDTO.getTrips().stream()
                .map(tripDTO -> {
                    Trip trip = new Trip();
                    trip.setId(tripDTO.getId());
                    trip.setName(tripDTO.getName());
                    trip.setStartTime(tripDTO.getStartTime());
                    trip.setEndTime(tripDTO.getEndTime());
                    trip.setStartPosition(tripDTO.getStartPosition());
                    trip.setPrice(tripDTO.getPrice());
                    trip.setCategory(tripDTO.getCategory());
                    trip.setGuide(this); // Assuming 'this' is the Guide entity being created or updated
                    return trip;
                })
                .collect(Collectors.toList());

    }

    public Guide(String firstName, String lastName, String email, String phone, int yearsOfExperience, List<Trip> trips) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.yearsOfExperience = yearsOfExperience;
        this.trips = trips;
    }

    public Guide(String firstName, String lastName, String email, String phone, int yearsOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.yearsOfExperience = yearsOfExperience;
    }
}
