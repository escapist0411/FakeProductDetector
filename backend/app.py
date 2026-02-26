import os
import numpy as np
from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from werkzeug.security import generate_password_hash, check_password_hash
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image

app = Flask(__name__)

# ==========================
# DATABASE CONFIG
# ==========================
app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///users.db"
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False

db = SQLAlchemy(app)

# ==========================
# USER MODEL
# ==========================
class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100))
    email = db.Column(db.String(100), unique=True)
    password = db.Column(db.String(200))

# Create DB
with app.app_context():
    db.create_all()

# ==========================
# LOAD ML MODEL
# ==========================
model = load_model("fake_product_detector_model.h5")
IMG_SIZE = 150

# ==========================
# AUTH ROUTES
# ==========================

@app.route("/signup", methods=["POST"])
def signup():
    data = request.json

    name = data.get("name")
    email = data.get("email")
    password = data.get("password")

    if User.query.filter_by(email=email).first():
        return jsonify({"message": "User already exists"}), 400

    hashed_password = generate_password_hash(password)

    new_user = User(
        name=name,
        email=email,
        password=hashed_password
    )

    db.session.add(new_user)
    db.session.commit()

    return jsonify({"message": "User created successfully"}), 201


@app.route("/login", methods=["POST"])
def login():
    data = request.json

    email = data.get("email")
    password = data.get("password")

    user = User.query.filter_by(email=email).first()

    if not user:
        return jsonify({"message": "User not found"}), 404

    if not check_password_hash(user.password, password):
        return jsonify({"message": "Invalid password"}), 401

    return jsonify({
        "message": "Login successful",
        "user_id": user.id,
        "name": user.name
    }), 200


# ==========================
# PREDICT ROUTE
# ==========================

@app.route("/predict", methods=["POST"])
def predict():
    if "file" not in request.files:
        return jsonify({"error": "No file uploaded"})

    file = request.files["file"]
    filepath = "temp.jpg"
    file.save(filepath)

    img = image.load_img(filepath, target_size=(IMG_SIZE, IMG_SIZE))
    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)
    img_array = img_array / 255.0

    prediction = model.predict(img_array)[0][0]

    os.remove(filepath)

    if prediction > 0.5:
        result = "REAL"
        confidence = float(prediction)
    else:
        result = "FAKE"
        confidence = float(1 - prediction)

    return jsonify({
        "prediction": result,
        "confidence": round(confidence * 100, 2)
    })


@app.route("/")
def home():
    return "Fake Product Detector Backend Running"


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)