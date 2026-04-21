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

import java.util.ArrayList;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // ============================
    // ADICIONAR IMAGEM
    // ============================
    public void addImage(Long hotelId, String url) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel não encontrado"));

        // 🔥 AGORA É STRING (NÃO HotelImage)
        if (hotel.getImages() == null) {
            hotel.setImages(new ArrayList<>());
        }

        hotel.getImages().add(url);

        hotelRepository.save(hotel);
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
    // DTO → ENTITY
    // ============================
    private void mapToEntity(HotelRequestDTO dto, Hotel hotel) {

        hotel.setName(dto.getName());
        hotel.setCity(dto.getCity());
        hotel.setPricePerNight(dto.getPricePerNight());
        hotel.setAddress(dto.getAddress());
        hotel.setDescription(dto.getDescription());
        hotel.setDescriptionHome(dto.getDescriptionHome());
        hotel.setImage(dto.getImage());
        hotel.setAmenities(dto.getAmenities());

        // 🔥 garante lista não nula
        if (hotel.getImages() == null) {
            hotel.setImages(new ArrayList<>());
        }

        if (hotel.getRating() == null) {
            hotel.setRating(new Rating(0.0, 0, "Novo"));
        }
    }

    // ============================
    // ENTITY → DTO
    // ============================
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
                hotel.getImages(), // ✔ já é List<String>
                hotel.getAmenities(),
                ratingDTO
        );
    }

    // Deletar Imagem do Hotel
    public void removeImage(Long hotelId, String imageUrl) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel não encontrado com id: " + hotelId)
                );

        if (hotel.getImages() != null) {
            hotel.getImages().remove(imageUrl);
        }

        hotelRepository.save(hotel);
    }
}