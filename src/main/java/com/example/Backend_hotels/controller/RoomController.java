//package com.example.Backend_hotels.controller;
//
//import com.example.Backend_hotels.dto.room.RoomRequestDTO;
//import com.example.Backend_hotels.dto.room.RoomResponseDTO;
//import com.example.Backend_hotels.service.RoomService;
//import com.example.Backend_hotels.service.ImageService;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@CrossOrigin(origins = "*")
//@RestController
////@RequestMapping("/api/v1")
//@RequestMapping("/api/v1/rooms")
//public class RoomController {
//
//    private final RoomService roomService;
//    private final ImageService imageService;
//
//    public RoomController(RoomService roomService,
//                          ImageService imageService) {
//        this.roomService = roomService;
//        this.imageService = imageService;
//    }
//
//    // ============================
//    // LISTAR QUARTOS POR HOTEL
//    // ============================
//    @GetMapping("/hotels/{hotelId}/rooms")
//    public ResponseEntity<List<RoomResponseDTO>> getRoomsByHotel(
//            @PathVariable Long hotelId) {
//
//        return ResponseEntity.ok(roomService.findByHotelId(hotelId));
//    }
//
//    // ============================
//    // BUSCAR ROOM POR ID
//    // ============================
//    @GetMapping("/rooms/{id}")
//    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Long id) {
//        return ResponseEntity.ok(roomService.findById(id));
//    }
//
//    // ============================
//    // CRIAR ROOM
//    // ============================
//    @PostMapping("/rooms")
//    public ResponseEntity<RoomResponseDTO> createRoom(
//            @RequestBody RoomRequestDTO dto) {
//
//        RoomResponseDTO createdRoom = roomService.createRoom(dto);
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(createdRoom);
//    }
//
//    // ============================
//    // UPLOAD IMAGEM DA ROOM
//    // ============================
//    @PostMapping("/rooms/{id}/images")
//    public ResponseEntity<String> uploadRoomImage(
//            @PathVariable Long id,
//            @RequestParam("file") MultipartFile file) {
//
//        String imageUrl = imageService.uploadImage(file, "hotels/rooms/" + id);
//
//        // Aqui você pode depois evoluir para salvar via service
//        return ResponseEntity.ok(imageUrl);
//    }
//}

// Novo

package com.example.Backend_hotels.controller;

import com.example.Backend_hotels.dto.room.RoomRequestDTO;
import com.example.Backend_hotels.dto.room.RoomResponseDTO;
import com.example.Backend_hotels.service.RoomService;
import com.example.Backend_hotels.service.ImageService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.util.List;
import com.example.Backend_hotels.dto.room.RoomResponseDTO;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomService roomService;
    private final ImageService imageService;

    public RoomController(RoomService roomService,
                          ImageService imageService) {
        this.roomService = roomService;
        this.imageService = imageService;
    }

    // ============================
    // CRIAR ROOM
    // ============================
    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<RoomResponseDTO> createRoom(
            @Valid @RequestBody RoomRequestDTO dto) {

        RoomResponseDTO createdRoom = roomService.createRoom(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdRoom);
    }

    // ============================
    // BUSCAR ROOM POR ID
    // ============================
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(
            @PathVariable Long id) {

        return ResponseEntity.ok(roomService.findById(id));
    }

    // ============================
    // ATUALIZAR ROOM
    // ============================
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomRequestDTO dto) {

        return ResponseEntity.ok(roomService.update(id, dto));
    }

    // ============================
    // DELETAR ROOM
    // ============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {

        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // UPLOAD IMAGEM DA ROOM
    // ============================
    @PostMapping("/{id}/images")
    public ResponseEntity<String> uploadRoomImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        String imageUrl = imageService.uploadImage(file, "hotels/rooms/" + id);
        return ResponseEntity.ok(imageUrl);
    }

    // ============================
// LISTAR QUARTOS DO HOTEL
// ============================
    @GetMapping("/{hotelId}/rooms")
    public ResponseEntity<List<RoomResponseDTO>> getRoomsByHotel(
            @PathVariable Long hotelId) {

        return ResponseEntity.ok(roomService.findByHotelId(hotelId));
    }
}