# 🤖 Smart Image Classifier (Jetpack Compose + TensorFlow Lite)

A lightweight, emulator-friendly Android app that classifies images using a pre-trained TensorFlow Lite (TFLite) model. Built entirely with **Jetpack Compose**, **Hilt for Dependency Injection**, and **ViewModel** for state management.


## 📸 Features

- 🖼️ Pick an image from your device or use a sample image
- 🤖 Classify image using TFLite model and show top label
- 💡 Modern UI with Jetpack Compose
- ⚙️ Handles permissions dynamically (compatible with Android 13+)
- 📦 Clean architecture using ViewModel + Hilt DI
- 🧪 Runs smoothly on both **emulators** and **physical devices**


## 🧰 Tech Stack

- **Jetpack Compose** – for modern, declarative UI
- **ViewModel & StateFlow** – for reactive state management
- **Hilt** – for dependency injection
- **TensorFlow Lite** – for on-device image classification
- **Activity Result API** – for handling permissions & image picking
