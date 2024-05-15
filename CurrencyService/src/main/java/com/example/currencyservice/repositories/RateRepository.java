package com.example.currencyservice.repositories;


import com.example.currencyservice.entities.CurrentRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<CurrentRate, Long> {

    @Query("SELECT r FROM CurrentRate r ORDER BY r.rateId DESC LIMIT 1")
    CurrentRate findLastRate();

}
