package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class House {
    private int id;
    private int ownerId;
    private String ownerName;
    private String name;
    private String location;
    private String price;
    private String type;
    private String bedrooms;
    private String bathrooms;
    private String kitchens;
    private String description;
    private List<String> amenities = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    private String status;
    private Timestamp createdAt;

    public House() {}

    public House(int id, String name, String location, String price,
                  String type, String bedrooms, String bathrooms, String kitchens,
                  String description, List<String> amenities, List<String> images) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.type = type;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.kitchens = kitchens;
        this.description = description;
        this.amenities = amenities;
        this.images = images;
    }

    public House(int id, int ownerId, String ownerName, String name, String location, String price,
                  String type, String bedrooms, String bathrooms, String kitchens,
                  String description, List<String> amenities, List<String> images,
                  String status, Timestamp createdAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.name = name;
        this.location = location;
        this.price = price;
        this.type = type;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.kitchens = kitchens;
        this.description = description;
        this.amenities = amenities;
        this.images = images;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBedrooms() { return bedrooms; }
    public void setBedrooms(String bedrooms) { this.bedrooms = bedrooms; }

    public String getBathrooms() { return bathrooms; }
    public void setBathrooms(String bathrooms) { this.bathrooms = bathrooms; }

    public String getKitchens() { return kitchens; }
    public void setKitchens(String kitchens) { this.kitchens = kitchens; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public String getImage() {
        return (images != null && !images.isEmpty()) ? images.get(0) : null;
    }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}