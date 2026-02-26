from tensorflow.keras.preprocessing.image import ImageDataGenerator

DATASET_PATH = "../dataset"

datagen = ImageDataGenerator(rescale=1./255)

generator = datagen.flow_from_directory(
    DATASET_PATH,
    target_size=(150,150),
    batch_size=1,
    class_mode='binary'
)

print("Class Indices:", generator.class_indices)
print("Total Images:", generator.samples)