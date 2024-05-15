package com.example.transactionsservice.repositories;

import com.example.transactionsservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAll();

    Optional<Client> findById(Long id);

}
