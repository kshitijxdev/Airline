package com.shukla.payload.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {

    @NotBlank(message = "City name is required")
    @Size(max=100, message = "City name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "City code is required")
    @Size(min=3, max=3, message = "City code must be exactly 3 characters")
    private String cityCode;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must contain 2 uppercase letters")
    private String countryCode;

    @NotBlank(message = "Country name is required")
    @Size(max=100, message = "Country name cannot exceed 100 characters")
    private String countryName;

    @Size(max=10, message = "Region code cannot exceed 10 characters")
    private String regionCode;

    @Size(max=50, message = "Time zone value cannot exceed 50 characters")
    private String timeZoneId;
}
