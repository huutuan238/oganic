# 🧩 Full Stack Web Application

A full-stack web application built with **Spring Boot (backend)** and **Vue.js (frontend)**.

---

## 📁 Project Structure

```
oganic/
├── backend/       # Spring Boot application
└── frontend/      # Vue.js application
```

---

## 🚀 Getting Started

### 🔧 Backend (Spring Boot)

1. Go to the backend folder:
   ```bash
   cd backend
   ```

2. Build and run the app:
   ```bash
   ./mvnw spring-boot:run
   ```

3. The backend will start at:
   ```
   http://localhost:8080
   ```

> Make sure Java and Maven are installed.

---

### 💻 Frontend (Vue.js)

1. Go to the frontend folder:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

4. Vue app will be available at:
   ```
   http://localhost:5173
   ```

> Make sure Node.js and npm are installed.

---

## 🔗 API & CORS

The Vue frontend communicates with the Spring Boot backend via REST API (typically at `/api` routes). Ensure **CORS** is enabled in the backend for frontend requests.

---

## ⚙️ Build for Production

- **Frontend:**
  ```bash
  npm run build
  ```

- Copy the generated `dist/` folder into `backend/src/main/resources/static/` to serve it via Spring Boot.

---

## 📦 Technologies Used

- 🔙 Spring Boot, Spring Web, Spring Data JPA, H2/PostgreSQL
- 🔜 Vue 3 + Vite
- 🧪 Axios, Vue Router, Pinia (or Vuex)

---

## 📄 License

This project is licensed under the MIT License.
