package com.example.Backend_hotels.controller;

import com.example.Backend_hotels.dto.hotel.HotelRequestDTO;
import com.example.Backend_hotels.dto.hotel.HotelResponseDTO;
import com.example.Backend_hotels.service.HotelService;
import com.example.Backend_hotels.service.ImageService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final ImageService imageService;

    public HotelController(HotelService hotelService,
                           ImageService imageService) {
        this.hotelService = hotelService;
        this.imageService = imageService;
    }

    // ============================
    // CRIAR HOTEL
    // ============================
    @PostMapping
    public ResponseEntity<HotelResponseDTO> createHotel(
            @RequestBody @Valid HotelRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hotelService.create(dto));
    }

    // ============================
    // LISTAR HOTÉIS
    // ============================
    @GetMapping
    public ResponseEntity<Page<HotelResponseDTO>> getHotels(
            @RequestParam(required = false) String city,
            @PageableDefault(size = 5) Pageable pageable
    ) {

        Page<HotelResponseDTO> hotels =
                (city != null && !city.isBlank())
                        ? hotelService.findByCity(city, pageable)
                        : hotelService.findAll(pageable);

        return ResponseEntity.ok(hotels);
    }

    // ============================
    // BUSCAR POR ID
    // ============================
    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> getHotelById(
            @PathVariable Long id) {

        return ResponseEntity.ok(hotelService.findById(id));
    }

    // ============================
// ATUALIZAR HOTEL
// ============================
    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> updateHotel(
            @PathVariable Long id,
            @RequestBody @Valid HotelRequestDTO dto) {

        return ResponseEntity.ok(hotelService.update(id, dto));
    }

    // ============================
// DELETAR HOTEL
// ============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {

        hotelService.delete(id);

        return ResponseEntity.noContent().build();
    }

    // ============================
    // UPLOAD IMAGEM
    // ============================
    @PostMapping("/{id}/images")
    public ResponseEntity<String> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {

        String imageUrl = imageService.uploadImage(file, "hotels/" + id);

        return ResponseEntity.ok(imageUrl);
    }
}