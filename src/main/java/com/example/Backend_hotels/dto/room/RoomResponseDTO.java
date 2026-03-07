package com.example.Backend_hotels.dto.room;

public class RoomResponseDTO {

    private Long id;
    private Integer roomNumber;
    private String type;
    private Integer beds;
    private Integer capacity;
    private Double price;
    private Boolean availability;
    private Long hotelId;

    public RoomResponseDTO() {}

    public RoomResponseDTO(Long id, Integer roomNumber, String type,
                           Integer beds, Integer capacity,
                           Double price, Boolean availability,
                           Long hotelId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.beds = beds;
        this.capacity = capacity;
        this.price = price;
        this.availability = availability;
        this.hotelId = hotelId;
    }

    // getters e setters
}