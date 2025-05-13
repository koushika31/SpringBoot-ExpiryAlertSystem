# Expiry Alert System

A dynamic web application that helps track and manage item expiry dates using **Spring Boot** for backend and **HTML/CSS/JS** (or **React**) for the frontend.

## 🔧 Features
- Add, update, and delete items with expiry dates.
- Mark items as wasted.
- View all active and wasted items.
- MySQL database integration.
- RESTful API endpoints.
- CORS enabled for frontend access.
- Error handling and input validation.

## 📁 Project Structure
ExpiryAlertProject/
├── backend/ # Spring Boot backend
│ ├── src/
│ ├── pom.xml
│ └── application.properties
├── frontend/ # Frontend app (HTML/CSS/JS or React)
│ ├── index.html
│ ├── style.css
│ └── app.js
└── assets/
├── expiryalertsystem.mp4
└── screenshots/


## 🔌 API Endpoints
- `GET /api/items` — Get all active items
- `GET /api/items/wasted` — Get all wasted items
- `POST /api/items` — Add a new item
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

# In backend folder
mvn spring-boot:run
📞 Contact
Name: Koushika R.M.

Role: Full Stack Developer

Email: rmkoushika3115@gmail.com

LinkedIn: Koushika R.M

---

### **2. `expiry-alert-system.yml`**

project:
  name: Expiry Alert System
  description: A web-based system to track and manage item expiry dates using Spring Boot and a frontend (HTML/CSS/JS or React).
  version: 1.0.0

features:
  - Add, update, delete items with expiry dates
  - Mark items as wasted
  - View all active and wasted items
  - MySQL database integration
  - RESTful API endpoints
  - CORS enabled for frontend access
  - Error handling and input validation

structure:
  backend:
    type: spring-boot
    files:
      - src/
      - pom.xml
      - application.properties
  frontend:
    type: html-css-js or react
    files:
      - index.html
      - style.css
      - app.js
  assets:
    - expiryalertsystem.mp4
    - screenshots/

tech_stack:
  backend:
    - Java
    - Spring Boot
    - Spring Data JPA
  database:
    - MySQL
  frontend:
    - HTML
    - CSS
    - JavaScript
  tools:
    - Maven
    - Lombok
    - Validation API
    - Git
    - GitHub

api_endpoints:
  - method: GET
    path: /api/items
    description: Get all active items
  - method: GET
    path: /api/items/wasted
    description: Get all wasted items
  - method: POST
    path: /api/items
    description: Add new item
  - method: PUT
    path: /api/items/{id}
    description: Update item
  - method: DELETE
    path: /api/items/{id}
    description: Delete item
  - method: POST
    path: /api/items/{id}/waste
    description: Mark item as wasted

run_instructions:
  backend:
    steps:
      - cd backend
      - mvn spring-boot:run

developer:
  name: Koushika R.M.
  role: Full Stack Developer
  email: rmkoushika3115@gmail.com
  linkedin: https://www.linkedin.com/in/koushika-r-m-68667725a
  strengths:
    - Ambitious
    - Responsible
  goals: Aspiring Software Engineer

media:
  demo_video: expiryalertsystem.mp4
  screenshots:
    - screenshots/home.png
    - screenshots/add-item.png
    - screenshots/wasted-items.png
