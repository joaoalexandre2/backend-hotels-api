package com.example.Backend_hotels.dto.hotel;

import java.util.List;

public class HotelResponseDTO {

    private Long id;
    private String name;
    private String city;
    private Double pricePerNight;
    private String address;
    private String description;
    private String descriptionHome;
    private String image;
    private List<String> images;
    private List<String> amenities;
    private RatingDTO rating;

    public HotelResponseDTO() {}

    public HotelResponseDTO(Long id, String name, String city, Double pricePerNight,
                            String address, String description, String descriptionHome,
                            String image, List<String> images,
                            List<String> amenities, RatingDTO rating) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.pricePerNight = pricePerNight;
        this.address = address;
        this.description = description;
        this.descriptionHome = descriptionHome;
        this.image = image;
        this.images = images;
        this.amenities = amenities;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public Double getPricePerNight() { return pricePerNight; }
    public String getAddress() { return address; }
    public String getDescription() { return description; }
    public String getDescriptionHome() { return descriptionHome; }
    public String getImage() { return image; }
    public List<String> getImages() { return images; }
    public List<String> getAmenities() { return amenities; }
    public RatingDTO getRating() { return rating; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
    public void setPricePerNight(Double pricePerNight) { this.pricePerNight = pricePerNight; }
    public void setAddress(String address) { this.address = address; }
    public void setDescription(String description) { this.description = description; }
    public void setDescriptionHome(String descriptionHome) { this.descriptionHome = descriptionHome; }
    public void setImage(String image) { this.image = image; }
    public void setImages(List<String> images) { this.images = images; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }
    public void setRating(RatingDTO rating) { this.rating = rating; }
}