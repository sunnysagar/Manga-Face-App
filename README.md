# 📱 MangaFace App

A modern Android app built with **Jetpack Compose**, **MVVM + Clean Architecture**, and **MediaPipe Face Detection**. MangaFace allows user authentication, fetches manga from an API, caches data locally, and includes a real-time face recognition screen.

---

## 🔍 Overview

**MangaFace** is a Single-Activity Android app that demonstrates:
- User sign-in and session management with Room DB
- Manga listing from a public API with pagination and offline caching
- Real-time face detection using the front camera and MediaPipe
- Modular, scalable, and testable code architecture

---

## ✨ Features

### 1. 🔐 Sign In Screen
- Email/password-based sign-in
- Auto-login using stored session
- Uses Room Database for local credential storage
- No bottom nav on Sign In screen
- Navigates to Home (with bottom nav) after sign-in

### 2. 🧭 Bottom Navigation 
- Two items using Jetpack Compose Navigation:
  - Manga Screen
  - Face Recognition Screen

### 3. 📚 Manga Screen 
- Fetch manga using [MangaVerse API](https://rapidapi.com/sagararofie/api/mangaverse-api)
- Pagination with lazy loading
- Room DB caching for offline access
- Show detailed description on item click

### 4. 🧠 Face Recognition 
- Uses CameraX with MediaPipe face detection
- Live detection using the front camera
- Shows a reference rectangle:
  - Green when face is inside
  - Red otherwise

### 5. 🧱 Architecture 
- Clean Architecture + MVVM
- Single-Activity structure
- Jetpack Compose UI throughout
- Hilt for Dependency Injection

---

## 🧰 Tech Stack

| Layer            | Technology                         |
|------------------|-------------------------------------|
| UI               | Jetpack Compose, Material 3         |
| Architecture     | MVVM + Clean Architecture           |
| Navigation       | Jetpack Navigation Component        |
| Local Storage    | Room Database                       |
| API              | Retrofit, OkHttp, Gson              |
| DI               | Hilt                                |
| Async            | Kotlin Coroutines + Flow            |
| Camera           | CameraX                             |
| Face Detection   | MediaPipe Face Detector (Google)    |

---

## 🔄 Clone the Repo

```bash
git clone https://github.com/your-username/MangaFace.git
cd MangaFace
```

### Prerequisites
- Android Studio Giraffe or newer
- Kotlin 1.9.x or above
- Min SDK: 26
- A real Android device or emulator with camera (for MediaPipe)
  
### Setup API Key
Get an API key from [RapidAPI - MangaVerse](https://rapidapi.com/sagararofie/api/mangaverse-api)

### Screenshots

### APK

### 👨‍💻 Developed By
### SUNNY SAGAR
