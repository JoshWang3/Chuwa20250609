package com.example.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    /**
     * Finds animals whose name contains the given keyword (case-insensitive).
     */
    @Query("SELECT a FROM Animal a " +
           "WHERE LOWER(a.name) LIKE CONCAT('%', LOWER(:kw), '%')")
    List<Animal> findByNameContaining(@Param("kw") String keyword);
}
