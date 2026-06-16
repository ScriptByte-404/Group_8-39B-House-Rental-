-- GharSathi House Rental - MySQL setup
-- Run this in MySQL Workbench or: mysql -u root -p < database/house_rental_system.sql

CREATE DATABASE IF NOT EXISTS house_rental_system;
USE house_rental_system;

-- Users table (customers, owners, and admins)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('customer', 'owner', 'both', 'admin') DEFAULT 'customer',
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Password resets table
CREATE TABLE IF NOT EXISTS password_resets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    otp VARCHAR(10) NOT NULL,
    expiry_time DATETIME NOT NULL
);

-- Houses table (updated with all required columns)
CREATE TABLE IF NOT EXISTS houses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner_id INT,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    price VARCHAR(50) NOT NULL,
    type VARCHAR(50),
    bedrooms VARCHAR(50),
    bathrooms VARCHAR(50),
    kitchens VARCHAR(50),
    description TEXT,
    amenities TEXT,
    images TEXT, -- Comma-separated image paths
    status ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Notices table (for admin announcements)
CREATE TABLE IF NOT EXISTS notices (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    posted_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (posted_by) REFERENCES users(id) ON DELETE SET NULL
);

-- Bookings table
CREATE TABLE IF NOT EXISTS bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    house_id INT NOT NULL,
    customer_id INT NOT NULL,
    status ENUM('pending', 'confirmed', 'cancelled') DEFAULT 'pending',
    booking_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (house_id) REFERENCES houses(id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Notifications table (per-user inbox)
CREATE TABLE IF NOT EXISTS notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    type VARCHAR(50),
    related_id INT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Activity log table (admin audit trail)
CREATE TABLE IF NOT EXISTS activity_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Migration: Add missing columns to existing houses table
-- Note: Run these individually if columns already exist to avoid errors
-- ALTER TABLE houses ADD COLUMN owner_id INT;
-- ALTER TABLE houses ADD COLUMN type VARCHAR(50);
-- ALTER TABLE houses ADD COLUMN bedrooms INT;
-- ALTER TABLE houses ADD COLUMN bathrooms INT;
-- ALTER TABLE houses ADD COLUMN kitchens INT;
-- ALTER TABLE houses ADD COLUMN description TEXT;
-- ALTER TABLE houses ADD COLUMN amenities TEXT;
-- ALTER TABLE houses ADD COLUMN images TEXT;
-- ALTER TABLE houses ADD COLUMN status ENUM('pending', 'approved', 'rejected') DEFAULT 'pending';
-- ALTER TABLE houses ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
-- ALTER TABLE houses ADD FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE SET NULL;

-- Migration: Add missing columns to existing users table
-- Note: Run these individually if columns already exist to avoid errors
-- ALTER TABLE users ADD COLUMN name VARCHAR(100);
-- ALTER TABLE users ADD COLUMN role ENUM('customer', 'owner', 'both', 'admin') DEFAULT 'customer';
-- ALTER TABLE users ADD COLUMN phone VARCHAR(20);
-- ALTER TABLE users ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Sample houses (for testing)
INSERT INTO houses (name, price, location, status)
SELECT 'Modern Villa', 'Rs. 25,000/month', 'Kathmandu', 'approved'
WHERE NOT EXISTS (SELECT 1 FROM houses WHERE name = 'Modern Villa');

INSERT INTO houses (name, price, location, status)
SELECT 'Cozy Apartment', 'Rs. 15,000/month', 'Lalitpur', 'approved'
WHERE NOT EXISTS (SELECT 1 FROM houses WHERE name = 'Cozy Apartment');

INSERT INTO houses (name, price, location, status)
SELECT '3BHK Flat', 'Rs. 35,000/month', 'Bhaktapur', 'approved'
WHERE NOT EXISTS (SELECT 1 FROM houses WHERE name = '3BHK Flat');
