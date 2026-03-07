package com.example.Backend_hotels.service;

import com.example.Backend_hotels.domain.Hotel;
import com.example.Backend_hotels.domain.Room;
import com.example.Backend_hotels.dto.room.RoomRequestDTO;
import com.example.Backend_hotels.dto.room.RoomResponseDTO;
import com.example.Backend_hotels.repository.HotelRepository;
import com.example.Backend_hotels.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository,
                       HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    // ============================
    // Criar Room
    // ============================
    public RoomResponseDTO createRoom(RoomRequestDTO dto) {

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel não encontrado"));

        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setType(dto.getType());
        room.setBeds(dto.getBeds());
        room.setCapacity(dto.getCapacity());
        room.setPrice(dto.getPrice());
        room.setAvailability(dto.getAvailability());
        room.setHotel(hotel);

        Room savedRoom = roomRepository.save(room);

        return convertToResponseDTO(savedRoom);
    }

    // ============================
    // Buscar por ID
    // ============================
    public RoomResponseDTO findById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room não encontrada"));

        return convertToResponseDTO(room);
    }

    // ============================
    // Buscar por Hotel
    // ============================
    public List<RoomResponseDTO> findByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // ============================
    // Conversão Entity → DTO
    // ============================
    private RoomResponseDTO convertToResponseDTO(Room room) {
        return new RoomResponseDTO(
                room.getId(),
                room.getRoomNumber(),
                room.getType(),
                room.getBeds(),
                room.getCapacity(),
                room.getPrice(),
                room.getAvailability(),
                room.getHotel().getId()
        );
    }
}