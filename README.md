# 🔍 Fake Product Detector (Android + AI + CNN)

An AI-powered Android application that detects whether an electronic product is **REAL or FAKE** using **Computer Vision and Deep Learning (CNN)**.

This system allows users to scan products using their phone camera or gallery and instantly receive authenticity prediction with confidence score.

---

# 📱 Features

✅ User Authentication (Login / Signup)
✅ Scan product using Camera or Gallery
✅ AI-based Fake Product Detection
✅ Confidence percentage display
✅ Risk level indicator (Low / Medium / High)
✅ Scan history tracking
✅ Professional Android UI with Sidebar Navigation
✅ Result visualization with color grading
✅ Backend API integration
✅ Local database storage

---

# 🧠 AI Model Features

• CNN (Convolutional Neural Network)
• Image Classification
• Real vs Fake prediction
• TensorFlow / Keras model
• Image preprocessing & augmentation
• Accuracy up to ~70% (expandable with larger dataset)

---

# 🏗️ System Architecture

```
Android App (Frontend)
        │
        │ Retrofit API
        ▼
Flask Backend (Python)
        │
        │ Loads CNN Model
        ▼
TensorFlow Model (.h5)
        │
        ▼
Prediction Result (REAL / FAKE)
```

---

# 🛠️ Technologies Used

## Frontend (Android)

* Kotlin
* Jetpack Compose
* Material 3 UI
* Retrofit (API calls)
* Navigation Component

## Backend

* Python
* Flask
* REST API

## Machine Learning

* TensorFlow
* Keras
* CNN Model
* OpenCV
* NumPy

## Database

* SQLite (local)
* JSON storage

## Tools

* Android Studio
* VS Code
* GitHub
* Git
* Python 3.10+

---

# 📂 Project Structure

```
FakeProductDetector/
│
├── android/                 # Android application
│
├── backend/                 # Flask backend
│   ├── app.py
│   ├── train_model.py
│   ├── model/
│   └── dataset/
│
├── dataset/
│   ├── real/
│   └── fake/
│
├── model/
│   └── fake_product_model.h5
│
└── README.md
```

---

# ⚙️ Installation Guide

## Step 1: Clone Repository

```
git clone https://github.com/escapist0411/FakeProductDetector.git
```

---

## Step 2: Setup Backend

```
cd backend
python -m venv venv
venv\Scripts\activate
pip install -r requirements.txt
python app.py
```

Backend runs on:

```
http://127.0.0.1:5000
```

---

## Step 3: Run Android App

Open in:

```
Android Studio
```

Run on:

• Physical Device OR
• Emulator

---

# 📊 Machine Learning Workflow

1. Dataset Collection
2. Image Preprocessing
3. Data Augmentation
4. CNN Model Training
5. Model Evaluation
6. Model Saving (.h5)
7. Backend Integration
8. Android Integration

---

# 📸 Prediction Output

Example:

```
Prediction: REAL
Confidence: 92%
Risk Level: LOW
```

---

# 🎯 Future Enhancements

• Barcode scanning
• Larger dataset
• Cloud deployment
• Firebase integration
• Multi-category detection
• Admin dashboard

---

# 👨‍💻 Author

**Name:** Shreyas Sadavarte 
**Project:** Final Year Engineering Project
**Branch:** Computer Enggineering

GitHub:
https://github.com/escapist0411

---

# 📜 License

This project is for educational and academic purposes.

---

# ⭐ Conclusion

Fake Product Detector is an AI-powered mobile application that helps users identify counterfeit products using Deep Learning and Computer Vision.

It demonstrates integration of:

• Android Development
• Machine Learning
• Backend API
• Real-world AI deployment

---

# ❤️ Thank You
