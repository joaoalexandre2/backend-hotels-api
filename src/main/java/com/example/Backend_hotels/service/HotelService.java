package com.example.Backend_hotels.service;

import com.example.Backend_hotels.domain.Hotel;
import com.example.Backend_hotels.domain.Rating;
import com.example.Backend_hotels.dto.hotel.HotelRequestDTO;
import com.example.Backend_hotels.dto.hotel.HotelResponseDTO;
import com.example.Backend_hotels.dto.hotel.RatingDTO;
import com.example.Backend_hotels.exception.ResourceNotFoundException;
import com.example.Backend_hotels.repository.HotelRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // ============================
    // CRIAR HOTEL
    // ============================
    public HotelResponseDTO create(HotelRequestDTO dto) {

        Hotel hotel = new Hotel();
        mapToEntity(dto, hotel);

        Hotel saved = hotelRepository.save(hotel);

        return mapToResponse(saved);
    }

    // ============================
    // LISTAR HOTÉIS
    // ============================
    public Page<HotelResponseDTO> findAll(Pageable pageable) {
        return hotelRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public Page<HotelResponseDTO> findByCity(String city, Pageable pageable) {
        return hotelRepository.findByCityIgnoreCase(city, pageable)
                .map(this::mapToResponse);
    }

    // ============================
    // BUSCAR POR ID
    // ============================
    public HotelResponseDTO findById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hotel não encontrado com id: " + id
                        )
                );

        return mapToResponse(hotel);
    }

    // ============================
// ATUALIZAR HOTEL
// ============================
    public HotelResponseDTO update(Long id, HotelRequestDTO dto) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hotel não encontrado com id: " + id
                        )
                );

        // Atualiza os campos
        mapToEntity(dto, hotel);

        Hotel updated = hotelRepository.save(hotel);

        return mapToResponse(updated);
    }

    // ============================
// DELETAR HOTEL
// ============================
    public void delete(Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hotel não encontrado com id: " + id
                        )
                );

        hotelRepository.delete(hotel);
    }

    // ============================
    // MÉTODOS DE MAPEAMENTO
    // ============================

    private void mapToEntity(HotelRequestDTO dto, Hotel hotel) {
        hotel.setName(dto.getName());
        hotel.setCity(dto.getCity());
        hotel.setPricePerNight(dto.getPricePerNight());
        hotel.setAddress(dto.getAddress());
        hotel.setDescription(dto.getDescription());
        hotel.setDescriptionHome(dto.getDescriptionHome());
        hotel.setImage(dto.getImage());
        hotel.setImages(dto.getImages());
        hotel.setAmenities(dto.getAmenities());

        // Se quiser permitir criação de rating inicial
        // pode criar um padrão aqui
        if (hotel.getRating() == null) {
            hotel.setRating(new Rating(0.0, 0, "Novo"));
        }
    }

    private HotelResponseDTO mapToResponse(Hotel hotel) {

        RatingDTO ratingDTO = null;

        if (hotel.getRating() != null) {
            ratingDTO = new RatingDTO(
                    hotel.getRating().getScore(),
                    hotel.getRating().getReviews(),
                    hotel.getRating().getLabel()
            );
        }

        return new HotelResponseDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getCity(),
                hotel.getPricePerNight(),
                hotel.getAddress(),
                hotel.getDescription(),
                hotel.getDescriptionHome(),
                hotel.getImage(),
                hotel.getImages(),
                hotel.getAmenities(),
                ratingDTO
        );


    }
}