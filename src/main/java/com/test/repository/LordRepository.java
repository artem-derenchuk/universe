package com.test.repository;


import com.test.model.Lord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LordRepository extends CrudRepository<Lord,Long> {

    @Query(value = "select max(a.id) from lords a",nativeQuery = true)
    Long findMaxId();

    List <Lord> findTop10ByOrderByAgeAsc();

    @Query(value = "select * from lords where lords.id not in (select nested.id from lords nested join planets p on nested.id = p.lord_id)",nativeQuery = true)
    List<Lord> findSlackerLords();
}
