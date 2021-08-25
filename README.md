# MNIST
## Document Specification
[Android ML Project](/docs/Android_ML_Project.pdf)

## Steps
### 1. MNIST datasets
This link contains MNIST datasets in png format: https://github.com/myleott/mnist_png. A subset of 30 images from test set has been chosen and they are located at assets/testset directory of the Android application. 
It´s possible to add or delete images. As long the images are located in the assets/testset folder, please rebuild the apk after the operation.

### 2. Machine Learning models
These models have been trained, using https://keras.io/api/datasets/mnist/ train set, to classify handwritten digits and they output a tflite file for this Android application.

a) Exp01
https://github.com/higa-daniel/ipe/tree/main/model/exp01

b) Exp02
https://github.com/higa-daniel/ipe/tree/main/model/exp02

### 3. Evaluation
* Exp 01 - Initial model
313/313 [==============================] - 5s 16ms/step - loss: 5.6524 - accuracy: 0.3342
[5.652446269989014, 0.334199994802475]

* Exp 02 - Improved model
313/313 [==============================] - 5s 15ms/step - loss: 0.2454 - accuracy: 0.9222
[0.2453950196504593, 0.9222000241279602]

Note that the accuracy presented refers to the model running python, tensorflow and x86 architecture.
For Android running arm and TFLite, the metrics may be change.

### 4. Android implementation
Open Project in Android Studio using:
https://github.com/higa-daniel/ipe/tree/main/android

### 5. Android API level requirements
* Mininum API Level Android 5.0 (minSdkVersion 21)
* Target API Level is Android 11 (targetSdkVersion 30)
