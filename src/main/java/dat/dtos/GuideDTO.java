package dat.dtos;

import dat.entities.Guide;
import dat.entities.Trip;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Setter
public class GuideDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int yearsOfExperience;
    private List<TripDTO> trips;  // TODO check if this is correct. should this med DTOs?


    // Constructor to convert from entity to DTO
    public GuideDTO(Guide guide) {
        this.id = guide.getId();
        this.firstName = guide.getFirstName();
        this.lastName = guide.getLastName();
        this.email = guide.getEmail();
        this.phone = guide.getPhone();
        this.yearsOfExperience = guide.getYearsOfExperience();
        this.trips = guide.getTrips().stream()
                .map(trip -> new TripDTO(
                        trip.getId(),
                        trip.getName(),
                        trip.getStartTime(),
                        trip.getEndTime(),
                        trip.getStartPosition(),
                        trip.getPrice(),
                        trip.getCategory()))
                .collect(Collectors.toList());

    }
}






