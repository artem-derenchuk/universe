package com.test.repository;

import com.test.model.Planet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends CrudRepository<Planet,Long> {
    @Query(value = "select max(a.id) from planets a",nativeQuery = true)
    Long findMaxId();

}
