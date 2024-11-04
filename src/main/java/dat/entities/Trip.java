package dat.entities;

import dat.enums.TripCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "trip")
@Setter
@AllArgsConstructor
@ToString
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;
    @Column (name = "name")
    private String name;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "start_position")
    private String startPosition;
    private double price;
    @Enumerated(EnumType.STRING)
    private TripCategory category;

    @ManyToOne(fetch = FetchType.LAZY) // fetch = FetchType.LAZY means that the guide will be loaded only when it is accessed
    @JoinColumn(name = "guide_id")
    private Guide guide;  // Each trip is led by one guide

// constructor to convert from DTO to entity
    public Trip(dat.dtos.TripDTO tripDTO) {
        this.id = tripDTO.getId();
        this.name = tripDTO.getName();
        this.startTime = tripDTO.getStartTime();
        this.endTime = tripDTO.getEndTime();
        this.startPosition = tripDTO.getStartPosition();
        this.price = tripDTO.getPrice();
        this.category = tripDTO.getCategory();
    }

    public Trip(String name, LocalDateTime startTime, LocalDateTime endTime, String startPosition, double price, TripCategory category, Guide guide) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startPosition = startPosition;
        this.price = price;
        this.category = category;
        this.guide = guide;
    }

    public Trip(String name, LocalDateTime startTime, LocalDateTime endTime, String startPosition, double price, TripCategory category) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startPosition = startPosition;
        this.price = price;
        this.category = category;
    }
}

