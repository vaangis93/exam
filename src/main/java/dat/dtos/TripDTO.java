package dat.dtos;

import dat.entities.Guide;
import dat.entities.Trip;
import dat.enums.TripCategory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Setter
public class TripDTO {


    private Long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String startPosition;
    private double price;
    private TripCategory category;
    private Guide guide;  // To represent the associated guide

    // Constructor to convert from entity to DTO
    public TripDTO(Trip trip) {
        this.id = trip.getId();
        this.name = trip.getName();
        this.startTime = trip.getStartTime();
        this.endTime = trip.getEndTime();
        this.startPosition = trip.getStartPosition();
        this.price = trip.getPrice();
        this.category = trip.getCategory();
        this.guide = trip.getGuide();

    }


    public TripDTO(Long id, String name, LocalDateTime startTime, LocalDateTime endTime, String startPosition, double price, TripCategory category) {
    }
}
