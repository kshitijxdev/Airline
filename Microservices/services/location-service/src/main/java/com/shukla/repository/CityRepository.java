package com.shukla.repository;

import com.shukla.model.City;
import com.shukla.payload.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City,Long> {

    boolean existsByCityCode(String cityCode);
    boolean existsByCityCodeAndIdNot(String cityCode, Long id);
    Page<City> findByCountryCodeIgnoreCase(String countryCode, Pageable pageable);

    @Query(value = """
            select*from city c where c.name ilike concat('%', :keyword, '%') or
            c.cityCode ilike concat('%', :keyword, '%') or
            c.countryCode ilike concat('%', :keyword, '%') or
            c.countryName ilike concat('%', :keyword, '%') or
            c.regionCode ilike concat('%', :keyword, '%')
            """,
            countQuery = """
                    select count(*) from city c where c.name ilike concat('%', :keyword, '%') or 
                    c.cityCode ilike concat('%', :keyword, '%') or
                    c.countryCode ilike concat('%', :keyword, '%') or
                    c.countryName ilike concat('%', :keyword, '%') or
                    c.regionCode ilike concat('%', :keyword, '%')
                    """,
            nativeQuery = true)
    Page<City> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
