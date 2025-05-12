# Spring Boot Expiry Alert System

A web-based application that helps track and manage item expiry dates using Spring Boot (Backend) and a frontend (HTML/CSS/JS or framework-based).

## 🔧 Features

- Add, update, delete items with expiry dates
- Mark items as wasted
- View all active and wasted items
- MySQL database integration
- RESTful API endpoints
- CORS enabled for frontend access
- Error handling and input validation

## 📁 Project Structure
ExpiryAlertProject/
├── backend/ # Spring Boot backend
│ ├── src/
│ ├── pom.xml
│ └── application.properties
├── frontend/ # Frontend app (HTML/CSS/JS or React/Vue/etc.)

## 🔌 API Endpoints

- `GET /api/items` — Get all active items
- `GET /api/items/wasted` — Get all wasted items
- `POST /api/items` — Add new item
- `PUT /api/items/{id}` — Update item
- `DELETE /api/items/{id}` — Delete item
- `POST /api/items/{id}/waste` — Mark item as wasted

## ⚙️ Technologies Used

- Java Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- Validation API
- Maven
- Git & GitHub

## 🚀 How to Run

```bash
# In backend folder
mvn spring-boot:run

