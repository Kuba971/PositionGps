package com.gddj.gps.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
public class GpsPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="position_id")
    private Long positionId;

    @Column(name="position_name")
    private String positionName;

    @Column(name="position_longitude")
    private Double positionLongitude;

    @Column(name="position_latitude")
    private Double positionLatitude;
}
