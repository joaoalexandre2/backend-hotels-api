package com.example.Backend_hotels.dto.hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

public class HotelRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Cidade é obrigatória")
    private String city;

    @NotNull(message = "Preço por noite é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double pricePerNight;

    @NotBlank(message = "Endereço é obrigatório")
    private String address;

    private String description;

    private String descriptionHome;

    private String image;

    private List<@NotBlank(message = "Imagem inválida") String> images = new ArrayList<>();

    private List<@NotBlank(message = "Amenidade inválida") String> amenities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionHome() {
        return descriptionHome;
    }

    public void setDescriptionHome(String descriptionHome) {
        this.descriptionHome = descriptionHome;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}