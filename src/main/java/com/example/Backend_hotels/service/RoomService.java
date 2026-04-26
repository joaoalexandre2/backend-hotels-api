//package com.example.Backend_hotels.service;
//
//import com.example.Backend_hotels.domain.Hotel;
//import com.example.Backend_hotels.domain.Room;
//import com.example.Backend_hotels.domain.RoomImage;
//import com.example.Backend_hotels.dto.room.RoomRequestDTO;
//import com.example.Backend_hotels.dto.room.RoomResponseDTO;
//import com.example.Backend_hotels.repository.HotelRepository;
//import com.example.Backend_hotels.repository.RoomRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class RoomService {
//
//    private final RoomRepository roomRepository;
//    private final HotelRepository hotelRepository;
//
//    public RoomService(RoomRepository roomRepository,
//                       HotelRepository hotelRepository) {
//        this.roomRepository = roomRepository;
//        this.hotelRepository = hotelRepository;
//    }
//
//    // ============================
//    // CRIAR ROOM
//    // ============================
//    public RoomResponseDTO createRoom(RoomRequestDTO dto) {
//
//        Hotel hotel = hotelRepository.findById(dto.getHotelId())
//                .orElseThrow(() -> new RuntimeException("Hotel não encontrado"));
//
//        Room room = new Room();
//        room.setRoomNumber(dto.getRoomNumber());
//        room.setType(dto.getType());
//        room.setBeds(dto.getBeds());
//        room.setCapacity(dto.getCapacity());
//        room.setPrice(dto.getPrice());
//        room.setAvailability(dto.getAvailability());
//        room.setHotel(hotel);
//
//        Room savedRoom = roomRepository.save(room);
//
//        return convertToResponseDTO(savedRoom);
//    }
//
//    // ============================
//    // ADICIONAR IMAGEM
//    // ============================
//    public void addImage(Long roomId, String url) {
//
//        Room room = roomRepository.findById(roomId)
//                .orElseThrow(() -> new RuntimeException("Room não encontrada"));
//
//        RoomImage image = new RoomImage(url, room);
//
//        room.getImages().add(image);
//
//        roomRepository.save(room);
//    }
//
//    // ============================
//    // BUSCAR POR ID
//    // ============================
//    public RoomResponseDTO findById(Long id) {
//
//        Room room = roomRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Room não encontrada"));
//
//        return convertToResponseDTO(room);
//    }
//
//    // ============================
//    // BUSCAR POR HOTEL
//    // ============================
//    public List<RoomResponseDTO> findByHotelId(Long hotelId) {
//
//        return roomRepository.findByHotelId(hotelId)
//                .stream()
//                .map(this::convertToResponseDTO)
//                .collect(Collectors.toList());
//    }
//
//    // ============================
//    // CONVERTER ENTITY → DTO
//    // ============================
//    private RoomResponseDTO convertToResponseDTO(Room room) {
//
//        List<String> images = room.getImages() != null
//                ? room.getImages()
//                .stream()
//                .map(RoomImage::getImageUrl)
//                .collect(Collectors.toList())
//                : List.of();
//
//        return new RoomResponseDTO(
//                room.getId(),
//                room.getRoomNumber(),
//                room.getType(),
//                room.getBeds(),
//                room.getCapacity(),
//                room.getPrice(),
//                room.getAvailability(),
//                room.getHotel().getId(),
//                images
//        );
//    }
//
//    // ============================
//    // ATUALIZAR ROOM
//    // ============================
//    public RoomResponseDTO update(Long id, RoomRequestDTO dto) {
//
//        Room room = roomRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Room não encontrada"));
//
//        Hotel hotel = hotelRepository.findById(dto.getHotelId())
//                .orElseThrow(() -> new RuntimeException("Hotel não encontrado"));
//
//        room.setRoomNumber(dto.getRoomNumber());
//        room.setType(dto.getType());
//        room.setBeds(dto.getBeds());
//        room.setCapacity(dto.getCapacity());
//        room.setPrice(dto.getPrice());
//        room.setAvailability(dto.getAvailability());
//        room.setHotel(hotel);
//
//        Room updatedRoom = roomRepository.save(room);
//
//        return convertToResponseDTO(updatedRoom);
//    }
//
//    // ============================
//    // DELETAR ROOM
//    // ============================
//    public void delete(Long id) {
//
//        if (!roomRepository.existsById(id)) {
//            throw new RuntimeException("Room não encontrada");
//        }
//
//        roomRepository.deleteById(id);
//    }
//}

// Novo 26/04/2026

package com.example.Backend_hotels.service;

import com.example.Backend_hotels.domain.Hotel;
import com.example.Backend_hotels.domain.Room;
import com.example.Backend_hotels.domain.RoomImage;
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
    // CRIAR ROOM
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
    // ADICIONAR IMAGEM (FIX)
    // ============================
    public void addImage(Long roomId, String url) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room não encontrada"));

        // ✔ CORRETO: usa construtor com (url, room)
        RoomImage image = new RoomImage(url, room);

        // ✔ usa método helper da entidade
        room.addImage(image);

        roomRepository.save(room);
    }

    // ============================
    // BUSCAR POR ID
    // ============================
    public RoomResponseDTO findById(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room não encontrada"));

        return convertToResponseDTO(room);
    }

    // ============================
    // BUSCAR POR HOTEL
    // ============================
    public List<RoomResponseDTO> findByHotelId(Long hotelId) {

        return roomRepository.findByHotelId(hotelId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // ============================
    // CONVERTER ENTITY → DTO (FIX)
    // ============================
    private RoomResponseDTO convertToResponseDTO(Room room) {

        List<String> images = room.getImages() != null
                ? room.getImages()
                .stream()
                .map(RoomImage::getImageUrl) // ✔ CORRETO (não é getUrl)
                .collect(Collectors.toList())
                : List.of();

        return new RoomResponseDTO(
                room.getId(),
                room.getRoomNumber(),
                room.getType(),
                room.getBeds(),
                room.getCapacity(),
                room.getPrice(),
                room.getAvailability(),
                room.getHotel().getId(),
                images
        );
    }

    // ============================
    // ATUALIZAR ROOM
    // ============================
    public RoomResponseDTO update(Long id, RoomRequestDTO dto) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room não encontrada"));

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel não encontrado"));

        room.setRoomNumber(dto.getRoomNumber());
        room.setType(dto.getType());
        room.setBeds(dto.getBeds());
        room.setCapacity(dto.getCapacity());
        room.setPrice(dto.getPrice());
        room.setAvailability(dto.getAvailability());
        room.setHotel(hotel);

        Room updatedRoom = roomRepository.save(room);

        return convertToResponseDTO(updatedRoom);
    }

    // ============================
    // DELETAR ROOM
    // ============================
    public void delete(Long id) {

        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room não encontrada");
        }

        roomRepository.deleteById(id);
    }
}