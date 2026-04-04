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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}