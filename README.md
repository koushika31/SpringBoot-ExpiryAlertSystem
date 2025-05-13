# 🛎️ Expiry Alert System

> A web-based application to **track and manage expiry dates** using **Spring Boot** (Backend) and a responsive **Frontend**.

---

## 🎥 Live Demo

https://github.com/yourusername/ExpiryAlertProject/assets/yourgithubid/expiryalertsystem.mp4  
<!-- Alternatively, if video is in repo: -->

<video width="100%" controls autoplay muted loop>
  <source src="expiryalertsystem.mp4" type="video/mp4">
  Your browser does not support the video tag.
</video>

---

## ✨ Features

- ➕ Add / 📝 Edit / ❌ Delete items with expiry dates  
- 🗑️ Mark items as wasted  
- 📄 View active and wasted items  
- 💾 MySQL Database Integration  
- 🔗 RESTful API Endpoints  
- 🔓 CORS Support  
- 🛡️ Input Validation & Error Handling  

---

## 🗂️ Project Structure


ExpiryAlertProject/
├── backend/                   # Spring Boot App
│   ├── src/
│   ├── pom.xml
│   └── application.properties
├── frontend/                  # HTML/CSS/JS or React/Vue
├── expiryalertsystem.mp4      # Demo Video
└── screenshots/               # Project Screenshots
🛠️ Tech Stack

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
  role: B.Tech IT Student
  email: rmkoushika3115@gmail.com
  linkedin: https://www.linkedin.com/in/koushika-r-m-68667725a
  strengths:
    - Ambitious
    - Responsible
  goals: Aspiring Software Engineer

media:
  demo_video: expiryalertsystem.mp4
  


