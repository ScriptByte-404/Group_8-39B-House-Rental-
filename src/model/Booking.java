package model;

public class Booking {
    private int id;
    private int houseId;
    private int customerId;
    private String status;       // pending | confirmed | cancelled
    private String bookingDate;
    private String houseName;
    private String location;
    private String price;
    private String imagePath;    // first image path from houses.images

    public Booking() {}

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getHouseId() { return houseId; }
    public void setHouseId(int houseId) { this.houseId = houseId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getHouseName() { return houseName; }
    public void setHouseName(String houseName) { this.houseName = houseName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    /** Returns the first image if stored as comma-separated */
    public String getFirstImage() {
        if (imagePath == null || imagePath.trim().isEmpty()) return null;
        return imagePath.split(",")[0].trim();
    }
}
