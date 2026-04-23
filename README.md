# 🌿 Leaflytics — Indoor Plant Care Tracker

Leaflytics is a simple full-stack web application designed to help users manage and monitor indoor plant care efficiently. It allows users to track watering schedules, monitor plant health, and read plant care blogs — all in one place.

---

## 🚀 Live Demo

* 🌐 Frontend: https://your-frontend.vercel.app
* ⚙️ Backend API: https://your-backend.onrender.com

---

## 📌 Features

### 🌱 Plant Management

* Add new plants with details (name, type, location, notes)
* View all plants in a clean dashboard
* Update plant information
* Delete plants
* Track:

  * Last watered date
  * Last fertilized date

---

### ⏰ Smart Reminder System

* Automatically calculates watering needs
* Displays:

  * ✅ **Healthy** (recently watered)
  * ⚠️ **Warning** (almost due)
  * ❌ **Critical** (overdue)
* Default watering interval: **3–7 days**

---

### 📊 Plant Health Dashboard

* Card-based UI for all plants
* Shows:

  * Plant name
  * Last watered date
  * Next watering date
  * Status (color-coded)

---

### 📝 Blog System

* Create blog posts
* View blog list
* Read individual blog posts

---

## 🧩 Tech Stack

### Frontend

* HTML
* CSS
* Vanilla JavaScript (Fetch API)

### Backend

* Java Spring Boot
* REST APIs

### Database

* MySQL
* JPA / Hibernate

---

## 🏗️ Project Structure

```
leaflytics/
│
├── backend/
│   ├── src/main/java/com/leaflytics/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── model/
│   ├── src/main/resources/
│   └── pom.xml
│
├── frontend/
│   ├── index.html
│   ├── add-plant.html
│   ├── blog.html
│   ├── blog-details.html
│   ├── css/
│   └── js/
│
└── README.md
```

---

## ⚙️ Backend Setup (Spring Boot)

### 1. Navigate to backend

```
cd backend
```

### 2. Configure MySQL

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/leaflytics
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3. Run the backend

```
mvn spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

---

## 🗄️ Database Schema

### Plant Table

* id (Primary Key)
* name
* type
* location
* notes
* last_watered_date
* last_fertilized_date

### BlogPost Table

* id (Primary Key)
* title
* content
* created_at

---

## 🌐 Frontend Setup

### 1. Navigate to frontend

```
cd frontend
```

### 2. Run frontend

* Open `index.html` directly in browser
  OR
* Use Live Server (recommended)

---

## 🔗 API Endpoints

### Plant APIs

```
GET    /api/plants
POST   /api/plants
GET    /api/plants/{id}
PUT    /api/plants/{id}
DELETE /api/plants/{id}
```

### Blog APIs

```
GET    /api/blogs
POST   /api/blogs
GET    /api/blogs/{id}
```

---

## 🧪 Sample Data

### Example Plant JSON

```
{
  "name": "Aloe Vera",
  "type": "Succulent",
  "location": "Bedroom",
  "notes": "Needs sunlight",
  "lastWateredDate": "2026-04-20",
  "lastFertilizedDate": "2026-04-10"
}
```

---

## 🚀 Deployment Guide

### ⚠️ Important

Vercel does NOT support Spring Boot backend directly.

---

### ✅ Recommended Deployment

| Layer    | Platform                      |
| -------- | ----------------------------- |
| Frontend | Vercel                        |
| Backend  | Render / Railway              |
| Database | MySQL (Railway / PlanetScale) |

---

### 🔹 Deploy Backend (Render)

1. Push backend to GitHub
2. Create a new Web Service on Render
3. Configure:

```
Build Command: mvn clean install
Start Command: java -jar target/*.jar
```

4. Add environment variables for DB

---

### 🔹 Deploy Frontend (Vercel)

1. Push frontend to GitHub
2. Import project in Vercel
3. Set root directory:

```
frontend/
```

---

### 🔹 Update API URL in frontend

```
const BASE_URL = "https://your-backend-url.onrender.com/api";
```

---

## 🧠 Key Learnings

* REST API development with Spring Boot
* CRUD operations using MySQL
* Frontend-backend integration using Fetch API
* Clean UI design with vanilla CSS
* Deployment of full-stack applications

---

## 📌 Future Improvements

* User authentication (JWT)
* Email reminders for watering
* Image upload for plants
* Advanced analytics dashboard

---

## 👨‍💻 Author

**Jerry Jeriomio Joydhor**

---

## ⭐ Acknowledgment

This project was built as a full-stack learning project to strengthen backend development, API integration, and deployment skills.
