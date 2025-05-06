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
git clone https://github.com/sunnysagar/Manga-Face-App.git
cd Manga-Face-App
```

### Prerequisites
- Android Studio Giraffe or newer
- Kotlin 1.9.x or above
- Min SDK: 26
- A real Android device or emulator with camera (for MediaPipe)
  
### Setup API Key
Get an API key from [RapidAPI - MangaVerse](https://rapidapi.com/sagararofie/api/mangaverse-api)

### Screenshots
![screen1](https://github.com/user-attachments/assets/1a5324d7-4a28-4329-bdcb-b6b5b4cc142d)
![screen2](https://github.com/user-attachments/assets/f9c00983-69ec-4054-aebd-efcf862e36f7)
![screen3](https://github.com/user-attachments/assets/7ce7493c-20ba-4746-878a-8406985df583)
 ![screen4](https://github.com/user-attachments/assets/6e3d068a-ef33-4393-8861-4f68c851e030)

### Screen-Recording and APK
[Drive-link](https://drive.google.com/drive/folders/1MZsmmtHmVk-F3rjmt5Wg6-B705tNG0z0?usp=sharing)

### 👨‍💻 Developed By
### SUNNY SAGAR
