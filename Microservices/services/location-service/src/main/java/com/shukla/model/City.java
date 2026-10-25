package com.shukla.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max=100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Size(min=3, max=3)
    @Column(nullable = false, length = 3)
    private String cityCode;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$")
    @Column(nullable = false, length = 2)
    private String countryCode;

    @NotBlank
    @Size(max=100)
    @Column(nullable = false,length = 100)
    private String countryName;

    @Size(max=10)
    @Column(length = 10)
    private String regionCode;

    @Size(max=50)
    @Column(name="time_zone_id", length = 50)
    private String timeZoneId;

}
