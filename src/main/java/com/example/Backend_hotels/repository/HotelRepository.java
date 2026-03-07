package com.example.Backend_hotels.repository;

import com.example.Backend_hotels.domain.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // Filtro por cidade com paginação
    Page<Hotel> findByCityIgnoreCase(String city, Pageable pageable);
}